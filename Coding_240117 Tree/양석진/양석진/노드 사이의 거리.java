import java.io.*;
import java.util.*;


public class Main {
    //트리 노드 정보 저장 클래스
    static class node{
        int next, length;
        public node(int next, int length){
            this.next = next;
            this.length = length;
        }
    }
    static ArrayList<ArrayList<node>> tree = new ArrayList<>();	//트리 정보 저장 리스트
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++)
            tree.add(new ArrayList<>());
        //입력되는 트리 정보 저장
        for(int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree.get(a).add(new node(b, w));
            tree.get(b).add(new node(a, w));
        }
        //M개의 노드 쌍 거리 구하기
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(search(a, b, N) + "\n");	//거리 BufferedWriter 저장
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }

    static int search(int start, int end, int n){
        Queue<node> q = new LinkedList<>();		//BFS에 사용할 Queue
        boolean[] visited = new boolean[n+1];
        visited[start] = true;	//시작 노드 방문 확인
        q.add(new node(start, 0));	//시작 노드 Queue 저장
        while(!q.isEmpty()){
            node cur = q.poll();
            if(cur.next == end)		//목적지 도착시
                return cur.length;
            //현재 노드 연결된 주변 노드 탐색
            for(node next : tree.get(cur.next)){
                if(!visited[next.next]){
                    visited[next.next] = true;
                    q.add(new node(next.next, cur.length + next.length));
                }
            }
        }
        return -1;
    }
}