package Solution;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface IndexedSolution<E> extends OptimizationSolution<E>, List<E> {

}

interface TreeSolution<E> extends OptimizationSolution<Node<E>>, Tree<E> {
	
	Node<E> first();
}

interface Tree<E> extends Collection<Node<E>>{
	
}

interface Node<E>{
	Node<E> superNode();
	
	E getHold();
	
	E setHold();
}

interface Branch<B> extends Node<B> {
	Collection<Node<B>> subNodes();
}

interface Leaf<L> extends Node<L> {
	
}

interface GraphSolution<E> extends OptimizationSolution<E>, Graph<E> {
	
}

interface Graph<E> extends Collection<E>{
	
	Collection<Vertex<E>> Verticies();
	
	Collection<Edge<E>> Edge();
}

interface Vertex<E>{
	
}

interface Edge<E>{
	
}