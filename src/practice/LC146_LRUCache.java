package practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * LC146 LRU Cache
 	doubly linked list
	hashmap <I, DNode> => key, dnode(key, val)
	size // cache size
	capacity
	
	get
	 => check if key doesnt exist? rt -1
	 => move to head
	 => rt value
	 
	put
	 if key exists?
	   => move to head and update
	
	  otherwise,
	   => size check & evict if necessary
	   => add to head
	   
	e.g.
	head <-> tail 
	head - (4,4) - (2,2) - (3,3)  - tail
 * @author sunnypark
 *
 */
public class LC146_LRUCache {
    DNode head;
    DNode tail;
    Map<Integer, DNode> cache;
    int capacity;
    
    public LC146_LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        
        head = new DNode(-1, -1);
        tail = new DNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        DNode node = cache.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        // update the value
        if (cache.containsKey(key)) {
            DNode nodeToUpdate = cache.get(key);
            moveToHead(nodeToUpdate);
            nodeToUpdate.val = value;
            return;
        }
        
        // check if it needs eviction
        // add new node
        if (cache.size() == capacity) {
            removeNode(tail.prev); // remove from DNode queue, cache
        }
        
        addNode(new DNode(key, value));
    }
    
    // remove from mid, add node
    private void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }
    
    // build DNode
    // add node to head
    // add to cache
    // increment size
    private void addNode(DNode newNode) {
        cache.put(newNode.key, newNode);
        
        DNode tmp = head.next;
        head.next = newNode;
        newNode.prev = head;
        
        newNode.next = tmp;
        tmp.prev = newNode;
    }
    
    // remove node from q AND cache
    // decrement size
    private void removeNode(DNode node) {
        DNode prev = node.prev;
        DNode next = node.next;
        
        prev.next = next;
        next.prev = prev;

        cache.remove(node.key);
    }
    
    private static class DNode {
        int key;
        int val;
        DNode prev;
        DNode next;
        
        // constructor
        DNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
