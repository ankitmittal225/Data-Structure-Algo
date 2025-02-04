package com.program;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class CovidTestingPlatform {
    Map<String, TestingCentre> centres= new ConcurrentHashMap<>();
    final int NUMBEROFNEARESTCENTRE= 3;


    public void init(List<TestingCentre> tcList){
        if(tcList.isEmpty()) return;
        for(TestingCentre tc: tcList){
            centres.put(tc.id, tc);
        }
    }


    public String book_test(String userId, int x, int y){
        User user= new User(userId, x, y);

        List<TestingCentre> nearestCentres= getNearestCentre(user);
        for(TestingCentre tc: nearestCentres){
            if(tc.bookTestKit(user)){
                return tc.id;
            }
        }

        return user.id+"- waitlist-"+nearestCentres.size()+" : "+nearestCentres;
    }

    public String receive_kit(String tcId){
        if(!centres.containsKey(tcId)) return null;
        TestingCentre tc= centres.get(tcId);
        User assignedUser= tc.receiveKit();
        if(assignedUser!=null){
            for(TestingCentre otherTC: getNearestCentre((assignedUser))){
                otherTC.waitingList.remove(assignedUser);
            }
            return assignedUser.id;
        }
        return null;
    }

    public  List<TestingCentre> getNearestCentre(User user) {
        PriorityQueue<TestingCentre> pq= new PriorityQueue<>(Comparator.comparingDouble(tc-> distance(user, tc)));

        pq.addAll(centres.values());
        List<TestingCentre> nearest= new ArrayList<>();
        int n= NUMBEROFNEARESTCENTRE;
        while(n-->0 && !pq.isEmpty()){
            nearest.add(pq.poll());
        }
        return nearest;

    }

    public double distance(User user, TestingCentre tc){
        return Math.sqrt(Math.pow(user.x-tc.x,2)+Math.pow(user.y-tc.y,2 ));
    }


}
