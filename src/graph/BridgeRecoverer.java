package graph;

public class BridgeRecoverer {

	UnionFinder unionFinder;

	public boolean isRecovered(Bridge... bridges) {
		unionFinder = new UnionFinder(bridges.length);
		Bridge firstBrdg = bridges[0];
		for (int i = 1; i < bridges.length; i++) {
			if (!isConnected(firstBrdg, bridges[i])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isConnected(Bridge b1, Bridge b2) {
		return unionFinder.isConnected(b1.from, b2.to);
	}

	public BridgeRecoverer recover(Bridge brdg) {
		unionFinder.union(brdg.from, brdg.to);
		return this;
	}

	private class Bridge {
		int from;
		int to;
	}
}
