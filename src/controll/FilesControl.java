package controll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FilesControl
{
    public boolean isValidPath(String path)
    {
        File dir = new File(path);
        return (dir.isDirectory() && dir.exists());
    }

    public boolean isValidFile(File file)
    {
        return (file.isFile() && file.exists());
    }

    public String getGroup(int columnNumber, String groupName, String fileName, String path) throws Exception {
        String line;
        String[] lines;
        StringBuilder result = new StringBuilder();
        if (!isValidPath(path))
            throw new Exception("Invalid Path");
        File file = new File(path, fileName);
        if (!isValidFile(file))
            throw new Exception("Invalid File");

        FileReader readFile = new FileReader(file);
        BufferedReader buffer = new BufferedReader(readFile);

        line = buffer.readLine();

        while (line != null)
        {
            lines = line.split(",");
            if (lines[columnNumber-1].equalsIgnoreCase(groupName))
                result.append(line).append("\n\r");
            line = buffer.readLine();
        }

        return result.toString();
    }

    private String space(int len)
    {
        StringBuilder result = new StringBuilder();
        if (len >= 50)
            return " ";
        result.append(" ");
        result.append(space(len+1));
        return result.toString();
    }

    public void showTable(int columnNumber, String groupName, String fileName, String path)
    {

        try {
            String group = getGroup(columnNumber, groupName, fileName, path);
            tryShowTable(columnNumber-1, group);
        } catch (Exception e) {
            System.err.println("Invalid");
        }
    }
    private void tryShowTable(int columnNumber, String group)
    {
        String[] lines = group.split("\n\r");
        String[] columns;
        int columnLenght;
        System.out.printf("FOOD NAME%sSCIENTIFIC NAME%sSUB GROUP\n", space("FOOD NAME".length()),
                space("SCIENTIFIC NAME".length()),
                space("SUB GROUP".length())
                );
        for (String line : lines)
        {
            columns = line.split(",");
            columnLenght = columns.length;
            for (int i = 0; i < columnLenght; i++)
            {
                if (i != columnNumber)
                    System.out.printf("%s%s", columns[i], space(columns[i].length()));
            }
            System.out.print("\n");
        }
    }
}
