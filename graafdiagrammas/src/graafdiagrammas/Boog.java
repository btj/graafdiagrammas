package graafdiagrammas;

import logicalcollections.LogicalSet;

/**
 * @invar | 0 <= getUitvalshoek() && getUitvalshoek() <= 359
 * @invar | getBronknoop() == null || getBronknoop().getUitgaandeBogen().contains(this)
 * @invar | getDoelknoop() == null || getDoelknoop().getInkomendeBogen().contains(this)
 */
public class Boog {
	
	/**
	 * @invar | 0 <= uitvalshoek
	 * @invar | uitvalshoek <= 359
	 * @invar | bronknoop == null || bronknoop.uitgaandeBogen.contains(this)
	 * @invar | doelknoop == null || doelknoop.inkomendeBogen.contains(this)
	 */
	int uitvalshoek;
	/**
	 * @peerObject
	 */
	Knoop bronknoop;
	/**
	 * @peerObject
	 */
	Knoop doelknoop;
	
	public int getUitvalshoek() { return uitvalshoek; }
	/**
	 * @peerObject
	 */
	public Knoop getBronknoop() { return bronknoop; }
	/**
	 * @peerObject
	 */
	public Knoop getDoelknoop() { return doelknoop; }

	/**
	 * @pre | 0 <= uitvalshoek && uitvalshoek <= 359
	 * @post | getUitvalshoek() == uitvalshoek
	 * @post | getBronknoop() == null
	 * @post | getDoelknoop() == null
	 */
	public Boog(int uitvalshoek) {
		this.uitvalshoek = uitvalshoek;
	}
	
	/**
	 * @pre | bronknoop != null
	 * @pre | getBronknoop() == null
	 * @mutates_properties | getBronknoop(), bronknoop.getUitgaandeBogen()
	 * @post | getBronknoop() == bronknoop
	 * @post | bronknoop.getUitgaandeBogen().equals(LogicalSet.plus(old(bronknoop.getUitgaandeBogen()), this))
	 */
	public void koppelBronknoop(Knoop bronknoop) {
		this.bronknoop = bronknoop;
		bronknoop.uitgaandeBogen.add(this);
	}
	
	/**
	 * @pre | getBronknoop() != null
	 * @mutates_properties | getBronknoop(), getBronknoop().getUitgaandeBogen()
	 * @post | getBronknoop() == null
	 * @post | old(getBronknoop()).getUitgaandeBogen().equals(LogicalSet.minus(old(getBronknoop().getUitgaandeBogen()), this))
	 */
	public void ontkoppelBronknoop() {
		bronknoop.uitgaandeBogen.remove(this);
		bronknoop = null;
	}
	
	/**
	 * @pre | doelknoop != null
	 * @pre | getDoelknoop() == null
	 * @mutates_properties | getDoelknoop(), doelknoop.getInkomendeBogen()
	 * @post | getDoelknoop() == doelknoop
	 * @post | doelknoop.getInkomendeBogen().equals(LogicalSet.plus(old(doelknoop.getInkomendeBogen()), this))
	 */
	public void koppelDoelknoop(Knoop doelknoop) {
		this.doelknoop = doelknoop;
		doelknoop.inkomendeBogen.add(this);
	}
	
	/**
	 * @pre | getDoelknoop() != null
	 * @mutates_properties | getDoelknoop(), getDoelknoop().getInkomendeBogen()
	 * @post | getDoelknoop() == null
	 * @post | old(getDoelknoop()).getInkomendeBogen().equals(LogicalSet.minus(old(getDoelknoop().getInkomendeBogen()), this))
	 */
	public void ontkoppelDoelknoop() {
		doelknoop.inkomendeBogen.remove(this);
		doelknoop = null;
	}
}
