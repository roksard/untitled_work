package roksard.algorithms.knNeighbours;

import java.util.*;

public class KNNeighbours {
    public static void main(String[] args) {
        KNNeighbours knn = new KNNeighbours();
        List<User> users = knn.getRandomUsers(5);
        for (User user : users) {
            knn.calcNeighbours(user, users);
            System.out.println(user);
            System.out.println();
        }

    }

    public List<User> getRandomUsers(int count) {
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            users.add(new User(random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10), new ArrayList<>()));
        }
        return users;
    }

    public double calcCosineSimilarity(User userA, User userB) {
        double mult = userA.getComedy() * userB.getComedy()
                + userA.getDocumental() * userB.getDocumental()
                + userA.getDrama() * userB.getDrama()
                + userA.getHorror() * userB.getHorror();
        double magnitudeA = Math.sqrt(
                userA.getComedy() * userA.getComedy()
                + userA.getDocumental() * userA.getDocumental()
                + userA.getDrama() * userA.getDrama()
                + userA.getHorror() * userA.getHorror());
        double magnitudeB = Math.sqrt(
                userB.getComedy() * userB.getComedy()
                + userB.getDocumental() * userB.getDocumental()
                + userB.getDrama() * userB.getDrama()
                + userB.getHorror() * userB.getHorror());
        return mult / (magnitudeA * magnitudeB);
    }

    public void calcNeighbours(User resultUser, List<User> users) {
        List<Neighbour> neighbours = resultUser.getNeighbours();
        for (User user : users) {
            if (user != resultUser) {
                neighbours.add(new Neighbour(user, calcCosineSimilarity(resultUser, user)));
            }
        }
        neighbours.sort(Comparator.comparingDouble(Neighbour::getSimilarity));
        Collections.reverse(neighbours);
    }
}
