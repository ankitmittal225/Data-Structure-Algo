package com.assignment;

import java.io.*;
import java.util.*;

public class SecretSanta {

    static class User {
        int id;
        String name;
        String email;

        User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) {
        List<User> users = readUsersFromCSV("users.csv");
        List<User> receivers = new ArrayList<>(users);
        Collections.shuffle(receivers);

        Map<User, User> assignments = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            assignments.put(users.get(i), receivers.get((i + 1) % users.size()));
        }

        writeAssignmentsToCSV("assignments.csv", assignments);
    }

    private static List<User> readUsersFromCSV(String fileName) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                String email = fields[2].trim();
                users.add(new User(id, name, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static void writeAssignmentsToCSV(String fileName, Map<User, User> assignments) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("giver_id,giver_name,giver_email,receiver_id,receiver_name,receiver_email\n");
            for (Map.Entry<User, User> entry : assignments.entrySet()) {
                User giver = entry.getKey();
                User receiver = entry.getValue();
                bw.write(String.format("%d,%s,%s,%d,%s,%s\n",
                        giver.id, giver.name, giver.email,
                        receiver.id, receiver.name, receiver.email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
