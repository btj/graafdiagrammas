package graafdiagrammas;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @invar | getUitgaandeBogen().stream().allMatch(b -> b != null && b.getBronknoop() == this)
 * @invar | getInkomendeBogen().stream().allMatch(b -> b != null && b.getDoelknoop() == this)
 */
public abstract class Knoop {
	
	/**
	 * @invar | uitgaandeBogen != null
     * @invar | inkomendeBogen != null
	 * @invar | uitgaandeBogen.stream().allMatch(b -> b != null && b.bronknoop == this)
     * @invar | inkomendeBogen.stream().allMatch(b -> b != null && b.doelknoop == this)
     * 
	 * @representationObject
	 * @peerObjects
	 */
	Set<Boog> uitgaandeBogen = new HashSet<>();
	/**
	 * @representationObject
	 * @peerObjects
	 */
	Set<Boog> inkomendeBogen = new HashSet<>();
	
	/**
	 * @peerObjects
	 * @creates | result
	 * @post | result != null
	 */
	public Set<Boog> getUitgaandeBogen() { return Set.copyOf(uitgaandeBogen); }
	
	/**
	 * @peerObjects
	 * @creates | result
	 * @post | result != null
	 */
	public Set<Boog> getInkomendeBogen() { return Set.copyOf(inkomendeBogen); }
	
	Knoop() {}
	
	/**
	 * @pre | info != null
	 * @pre | 2 <= info.length
	 * @inspects | this
	 * @mutates | info
	 * @post | 1 <= info[0]
	 * @post | 1 <= info[1]
	 */
	public abstract void berekenInfo(int[] info);
	
	public abstract boolean isIsomorfMet(Knoop andere);

	public Iterator<Knoop> getVolgendeKnopenIterator() {
		return new Iterator<>() {
			Iterator<Boog> uitgaandeBogenIterator = uitgaandeBogen.iterator();
			@Override
			public boolean hasNext() {
				return uitgaandeBogenIterator.hasNext();
			}
			@Override
			public Knoop next() {
				return uitgaandeBogenIterator.next().getDoelknoop();
			}
		};
	}
	
	public void forEachVolgendeKnoop(Consumer<? super Knoop> consumer) {
		for (Boog boog : uitgaandeBogen)
			if (boog.getDoelknoop() != null)
				consumer.accept(boog.getDoelknoop());
	}
	
	public Stream<Boog> getUitgaandeBogenVanVolgendeKnopenStream() {
		return uitgaandeBogen
				.stream()
				.map(b -> b.getDoelknoop())
				.filter(k -> k != null)
				.flatMap(k -> k.uitgaandeBogen.stream());
	}
	
}
