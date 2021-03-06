import java.util.*;
class AlienOrder {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        HashMap<Character, List<Character>> neighbors = new HashMap<>();
        HashMap<Character, Integer> degree = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            // char[] chars = words[i];
            char[] chars = words[i].toCharArray();
            for (char c: chars) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char a = word1.charAt(j);
                char b = word2.charAt(j);
                if (a != b) {                       // 忘了判断两个相同了
                    if (!neighbors.containsKey(a)) {
                        neighbors.put(a, new ArrayList<Character>());
                    }
                    if (!neighbors.get(a).contains(b)) {
                        neighbors.get(a).add(b);
                        degree.put(b, degree.get(b) + 1);
                    }
                    break; // 这个很关键 比到不一样的地方 后面再也不看了 避免za zb ca后面有加入了b->c
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (Character c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.offer(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char cur = (char) queue.poll();
            sb.append(cur);
            if (neighbors.containsKey(cur)) {           // 没判断neighbor里没有这个字的时候 null pointer
                for (Character neighbor : neighbors.get(cur)) {
                    degree.put(neighbor, degree.get(neighbor) - 1);
                    if (degree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        if (sb.length() == degree.size()) {
            return sb.toString();
        }
        return "";
    }
    public static void main(String[] args) {
        AlienOrder a = new AlienOrder();
        String[] words = {"wrt","wrf","er","ett","rftt"};
        System.out.println(a.alienOrder(words));
    }
}