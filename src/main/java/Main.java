import java.io.File;
import java.io.FileNotFoundException;
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

        components = gradleFiles.stream()
                .map(folder -> new Component(folder.substring(0, folder.length() - 1), new ArrayList<String>()))
                .collect(Collectors.toList());

        allFiles.stream().map(file -> {
            Component component = components.stream().filter(c -> file.startsWith(c.fullyQualifiedName))
                    .collect(Collectors.toList()).get(0);

        });
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
