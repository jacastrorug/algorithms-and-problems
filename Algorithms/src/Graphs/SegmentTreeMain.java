package Graphs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class SegmentTreeMain {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter(System.out);
		String linea = in.readLine();
		while(linea!=null&&!linea.equals("")){
			StringTokenizer toks = new StringTokenizer(linea);
			int a =Integer.parseInt(toks.nextToken());
			int arr[]= new int [a];
			for (int i = 0; i < arr.length; i++) {
				arr[i]=Integer.MAX_VALUE;
			}
			SegmentTree seg=new SegmentTree();
			int[] segment= seg.crearSegmentTree(arr);
			int[]lazy= new int [arr.length*4];
			SegmentTreeL prueba = new SegmentTreeL(arr);
			int q=Integer.parseInt(toks.nextToken());
			for (int i = 0; i < q; i++) {
				toks = new StringTokenizer(in.readLine());
				int t = Integer.parseInt(toks.nextToken());
				int x = Integer.parseInt(toks.nextToken());
				int y = Integer.parseInt(toks.nextToken());
				if(t==0)
					//(arreglo,0,tamañoOriginal-1,minimo rango,max rango,valor,pos)
//					seg.actualizarSegmentTreeLazy(segment,lazy,0,arr.length-1,x, x,y,0);
					prueba.update(x, x, y);
				else{
					out.println(prueba.query(x, y));
//					out.print(seg.buscarSegmentTreeLazy(segment,lazy, 0, arr.length-1, x, y, 0)+"\n");
				}
			}
			linea=in.readLine();
		}
		out.close();
	}
}
class SegmentTreeL {
int N;
    Node[] tree;
    public SegmentTreeL(int[] A) {
        N = A.length;
        int power = (int) Math.floor(Math.log(A.length) / Math.log(2)) + 1;
        int len = 2 * (int) Math.pow(2, power);
        tree = new Node[len];
        build(1, 0, A.length - 1, A);
    }
    private void build(int node, int l, int r, int[] A) {
        if (l == r){    // nodo hoja
            tree[node] = new Node(l, r);
            tree[node].value = A[l];
            return;
        }
        int leftChild = node * 2, rightChild = leftChild + 1, mid = (l + r) / 2;
        build(leftChild, l, mid, A); // calcular el valor del hijo izquierdo
        build(rightChild, mid + 1, r, A); // calcular el valor del hijo derecho
        tree[node] = new Node(l, r);
        tree[node].merge(tree[leftChild], tree[rightChild]);
    }
    private Node query(int node, int l, int r, int i, int j) {
        int leftChild = 2 * node, rightChild = leftChild + 1, mid = (l + r) / 2;
        if (l >= i && r <= j) {
            if (tree[node].flag)
                tree[node].split(leftChild, rightChild);
            return tree[node];
        }
        if (j < l || i > r)    return null;
        if (tree[node].flag)
            tree[node].split(leftChild, rightChild); // nodo visitado
        Node a = query(leftChild, l, mid, i, j);
        Node b = query(rightChild, mid + 1, r, i, j);
        Node temp = new Node(N, N);
        temp.merge(a, b);
        return temp;
    }
    public int query(int i, int j) {
        Node result = query(1, 0, N - 1, i, j);
        return result.value;
    }
    private void update(int node, int l, int r, int i, int j, int v) {
        int leftChild = 2 * node, rightChild = leftChild + 1, mid = (l + r) / 2;
        if (i <= l && j >= r) {
            tree[node].updated = v;
            tree[node].flag = true;
            tree[node].split(leftChild, rightChild);
        } else if (j < l || i > r)
            return;
        else {
            update(leftChild, l, mid, i, j, v);
            update(rightChild, mid + 1, r, i, j, v);
            tree[node].merge(tree[leftChild], tree[rightChild]);
        }
    }
    public void update(int i, int j, int newValue) {
        update(1, 0, N - 1, i, j, newValue);
    }
    class Node {
        boolean flag;
        int left, right, value, updated;
        public Node(int l, int r) {
            left = l;
            right = r;
            }
        public void merge(Node leftChild, Node rightChild) {
            if (leftChild == null)
                value = rightChild.value;
            else if (rightChild == null)
                value = leftChild.value;
            else
                value = Math.min(leftChild.value, rightChild.value);
        }
        public void split(int nL, int nR) {
        // marcar los hijos
            if (left != right) {
                Node leftChild = tree[nL];
                leftChild.flag = true;
                leftChild.updated = updated;
                Node rightChild = tree[nR];
                rightChild.flag = true;
                rightChild.updated = updated;
            }
            flag = false;
            value = updated;
        }
    }
}

 class SegmentTree {
	public  int[] crearSegmentTree(int []arr){
		int segmentTree[]= new int [arr.length*4];
		for (int i = 0; i < segmentTree.length; i++) {
			segmentTree[i]=Integer.MAX_VALUE;
		}
		construirSegmentTree(arr,segmentTree,0,arr.length-1,0);
		return segmentTree;
	}
	public  void construirSegmentTree(int []original, int []segTree,int menor,int mayor, int pos){
		if(menor==mayor ){
			segTree[pos]=original[menor];
			return;
		}
		int mitad =(menor+mayor)/2;
		construirSegmentTree(original,segTree,menor,mitad,2*pos+1);
		construirSegmentTree(original,segTree,mitad+1,mayor,2*pos+2);
		segTree[pos]=Math.min(segTree[2*pos+1], segTree[2*pos+2]);
	}
	public  int  buscarSegmentTree ( int []segTree,int menor,int mayor,int busmenor,int busmayor,int pos){
		if(menor>=busmenor&&mayor<=busmayor){
			return segTree[pos];
		}
		if(busmayor<menor||busmenor>mayor)
		return Integer.MAX_VALUE;
		int mid=(menor+mayor)/2;
		int izq=buscarSegmentTree(segTree, menor, mid, busmenor, busmayor, 2*pos+1);
		int der=buscarSegmentTree (segTree, mid+1, mayor, busmenor, busmayor, 2*pos+2);
		return Math.min(izq, der);
	}
	public  void   actualizarSegmentTree ( int []segTree,int menor,int mayor,int menorRango,int mayorRango,int cambio,int pos){
		if(menorRango>mayor||mayorRango<menor||menor>mayor)
			return;
		if(menor==mayor){
		segTree[pos]=cambio;
		return;
		}
		int mid=(menor+mayor)/2;
		actualizarSegmentTree(segTree, menor, mid, menorRango, mayorRango,cambio, 2*pos+1);
		actualizarSegmentTree (segTree, mid+1, mayor, menorRango, mayorRango,cambio, 2*pos+2);
		segTree[pos]=Math.min(segTree[2*pos+1], segTree[2*pos+2]);
	}
	
	public  void   actualizarSegmentTreeLazy ( int []segTree,int [] lazy ,int menor,int mayor,int menorRango,int mayorRango,int cambio,int pos){
		if(menorRango>mayor||mayorRango<menor||menor>mayor)
			return;
		if(lazy[pos]!=0){
			segTree[pos]=lazy[pos];
			if(menor!=mayor){
				lazy[2*pos+1]=lazy[pos];
				lazy[2*pos+2]=lazy[pos];
			}
			lazy[pos]=0;
		}
		if(menor>=menorRango&&mayor<=mayorRango){
			segTree[pos]=cambio;
			if(menor!=mayor){
				lazy[2*pos+1]=cambio;
				lazy[2*pos+2]=cambio;
			}
			return;
		}
		int mid=(menor+mayor)/2;
		actualizarSegmentTreeLazy(segTree,lazy, menor, mid, menorRango, mayorRango,cambio, 2*pos+1);
		actualizarSegmentTreeLazy (segTree,lazy, mid+1, mayor, menorRango, mayorRango,cambio, 2*pos+2);
		segTree[pos]=Math.min(segTree[2*pos+1], segTree[2*pos+2]);
	}
	public  int    buscarSegmentTreeLazy ( int []segTree,int [] lazy ,int menor,int mayor,int menorRango,int mayorRango,int pos){
		if(menorRango>mayor||mayorRango<menor||menor>mayor)
			return Integer.MAX_VALUE;
		if(lazy[pos]!=0){
			segTree[pos]=lazy[pos];
			if(menor!=mayor){
				lazy[2*pos+1]=lazy[pos];
				lazy[2*pos+2]=lazy[pos];
			}
			lazy[pos]=0;
		}
		if(menor>=menorRango&&mayor<=mayorRango){
			return segTree[pos];
		}
		int mid=(menor+mayor)/2;
		int izq=buscarSegmentTreeLazy(segTree,lazy, menor, mid, menorRango, mayorRango, 2*pos+1);
		int der =buscarSegmentTreeLazy (segTree,lazy, mid+1, mayor, menorRango, mayorRango, 2*pos+2);
		return Math.min(izq, der);
	}
	
	
	
//	public static void main(String[] args) {
//		SegmentTree seg=new SegmentTree();
//		int a[]={-1,2,4,1,7,1,3,2};
//		int[] segment= seg.crearSegmentTree(a);
//		
//		System.out.println(Arrays.toString(segment));
//		System.out.println(seg.buscarSegmentTree(segment, 0, a.length-1, 1, 2, 0));
//		seg.actualizarSegmentTree(segment,0,a.length-1,1,5,5,0);
//		System.out.println(Arrays.toString(segment));
//		System.out.println(seg.buscarSegmentTree(segment, 0, a.length-1, 0, 6, 0));
//		
//		segment= seg.crearSegmentTree(a);
//		int []lazy = new int [segment.length];
//		System.out.println(Arrays.toString(segment)+"\n"+Arrays.toString(lazy));
//		seg.actualizarSegmentTreeLazy(segment,lazy,0,a.length-1,1,5,5,0);
//		System.out.println(Arrays.toString(segment)+"\n"+Arrays.toString(lazy));
//	}
}



