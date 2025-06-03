package hashmap.implementation;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapCode {

    static class HashMap<K, V> {
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.value = value;
                this.key = key;

            }

        }

        int n; //total number of nodes
        int N; // total number of buckets
        private LinkedList<Node> buckets[];

        public HashMap() {
            this.N = 4;
            this.buckets = new LinkedList[4];
            for (int i = 0; i < 4; i++) {
                buckets[i] = new LinkedList<>();
            }
        }


        private void rehash() {
            LinkedList<Node> oldBuckets[] = buckets;
            buckets = new LinkedList[2 * N];

            for (int i = 0; i < 2 * N; i++) {
                buckets[i] = new LinkedList<>();
            }

            for (int i = 0; i < oldBuckets.length; i++) {
                LinkedList<Node> currentBucket = oldBuckets[i];
                for (int j = 0; j < currentBucket.size(); j++) {
                    Node currNode = currentBucket.get(j);
                    put(currNode.key, currNode.value);
                }
            }
            N *= 2;
        }

        private int findIndex(int bucketIndex, K key) {
            LinkedList<Node> ll = buckets[bucketIndex];
            for (int i = 0; i < ll.size(); i++) {
                Node node = ll.get(i);
                if (node.key == key)
                    return i;
            }
            return -1;
        }

        private int hashFunction(K key) {
            int hash = key.hashCode();
            return Math.abs(hash) % N;
        }

        public void put(K key, V value) {
            int bucketIndex = hashFunction(key);
            int dataIndex = findIndex(bucketIndex, key);
            if (dataIndex == -1) {
                Node node = new Node(key, value);
                buckets[bucketIndex].add(node);
                n++;
            } else {
                Node node = buckets[bucketIndex].get(dataIndex);
                node.value = value;
            }

            double lambda = (double) n / N;
            if (lambda > 2.0) {
                rehash();
            }
        }

        public boolean containsKey(K key) {
            int bucketIndex = hashFunction(key);
            int dataIndex = findIndex(bucketIndex, key);
            if (dataIndex == -1) {
                return false;
            } else {
                return true;
            }
        }

        public V get(K key) {
            int bucketIndex = hashFunction(key);
            int dataIndex = findIndex(bucketIndex, key);
            if (dataIndex == -1) {
                return null;
            } else {
                return buckets[bucketIndex].get(dataIndex).value;
            }
        }

        public V remove(K key) {
            int bucketIndex = hashFunction(key);
            int dataIndex = findIndex(bucketIndex, key);
            if (dataIndex == -1) {
                return null;
            } else {
                Node node = buckets[bucketIndex].remove(dataIndex);
                n--;
                return node.value;
            }
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();

            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    keys.add(buckets[i].get(j).key);
                }
            }
            return keys;
        }


        public boolean isEmpty() {
            return n == 0;
        }


    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 200);
        map.put("China", 190);
        map.put("Nepal", 65);

        ArrayList<String> keys = map.keySet();
        for (String k : keys) {
            System.out.println("Key: " + k + " value: " + map.get(k));
        }

        map.remove("Nepal");
        System.out.println(map.get("Nepal"));

    }
}
