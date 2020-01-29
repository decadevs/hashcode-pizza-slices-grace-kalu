package com.codeWithMerald;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MorePizza {

    public static void Pizzeria(){
        Path path = Paths.get("src/com/codeWithMerald/file.txt");
        try{
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String[] max = lines.get(0).split(" ");
            String[] slices = lines.get(1).split(" ");
            int maxSlices = Integer.parseInt(max[0]);

            int sum = 0;
            ArrayList<Integer> indicesOfTypes = new ArrayList<>();
            for(int i = slices.length-1; i>= 0; i--){
//                for (int j = slices.length - 2; i >= 0; i--) {
                    if (sum + Integer.parseInt(slices[i]) <= maxSlices) {
                        sum += Integer.parseInt(slices[i]);
                        indicesOfTypes.add(i);
                    } else {
                        continue;
                    }
//                    if((Integer.parseInt(slices[i]) + Integer.parseInt(slices[j])) == maxSlices ) {
//                        sum += Integer.parseInt(slices[i]) + Integer.parseInt(slices[j]);
//                        break;
//                    } else if (sum + Integer.parseInt(slices[i]) <= maxSlices) {
//                        sum += Integer.parseInt(slices[i]);
//                        index.add(i);
//                    }
//                    else {
//                        continue;
//                    }
//                }
                Collections.reverse(indicesOfTypes);
                StringBuilder stringBuilder = new StringBuilder();
                indicesOfTypes.forEach(index -> {
                    stringBuilder.append(index);
                    stringBuilder.append(" ");
                });
                Path newPath = Paths.get("src/com/codeWithMerald/pizza.txt");
                OutputStream outputStream = Files.newOutputStream(newPath);
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
                    String noOfSlice = String.valueOf(indicesOfTypes.size());
                    writer.write(noOfSlice);
                    writer.write('\n');
                    writer.write(String.valueOf(stringBuilder));
                    writer.flush();
                }
            }
            System.out.println(sum + "points");
        } catch (Exception exception) {
            System.err.println("Exception: "+ exception.getMessage());
        }

    }
}
