import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        List<String> allFiles = new ArrayList<>();
        List<String> gradleFiles = new ArrayList<>();
        List<Component> components = new ArrayList<>();

        if(args.length != 2){
            System.out.println("Introduce both parameters");
            return;
        }

        String outputFile = args[0];
        String filesToGroup = args[1];

        try{
            Scanner reader = new Scanner(new File(filesToGroup));

            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                allFiles.add(data);
            }

            gradleFiles = allFiles.stream()
                    .filter(f -> f.endsWith(".gradle"))
                    .map(f -> f.substring(0, f.length() - 7))
                    .filter(file -> file.length() != 0)
                    .collect(Collectors.toList());

            Set<String> set = new HashSet<>(gradleFiles);
            gradleFiles.clear();
            gradleFiles.addAll(set);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        components = gradleFiles
                .stream()
                .map(folder -> {
                    int index = folder.lastIndexOf("/");
                    if(index != -1)
                        return new Component(folder.substring(0, index), new ArrayList<String>());
                    return null;
                })
                .filter(file -> {
                    return file != null;
                })
                .collect(Collectors.toList());

        List<Component> finalComponents = components;
        Component defaultComponent = new Component("@", new ArrayList<>());
        allFiles.stream().forEach(file -> {
           Component component = null;

                    if(finalComponents.stream().filter(c -> file.startsWith(c.getFullyQualifiedName()))
                    .collect(Collectors.toList()).size() > 0) {
                     component = finalComponents.stream().filter(c -> file.startsWith(c.getFullyQualifiedName()))
                                .collect(Collectors.toList()).get(0);
                    }
            if(component != null) {
                component.getFiles().add(file);
            } else {
                defaultComponent.files.add(file);
            }
        });

        components.add(defaultComponent);

        try (FileWriter file = new FileWriter(outputFile)) {

            file.write(new ObjectMapper().writeValueAsString(components));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Component{
    String fullyQualifiedName;
    List<String> files;

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public Component(String fullyQualifiedName, List<String> files) {
        this.fullyQualifiedName = fullyQualifiedName;
        this.files = files;
    }
}
