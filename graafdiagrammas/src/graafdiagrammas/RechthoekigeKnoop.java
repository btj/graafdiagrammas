package graafdiagrammas;

/**
 * @invar | 1 <= getBreedte()
 * @invar | 1 <= getHoogte()
 */
public class RechthoekigeKnoop extends Knoop {
	
	/**
	 * @invar | 1 <= breedte
	 * @invar | 1 <= hoogte
	 */
	int breedte;
	int hoogte;
	
	public int getBreedte() { return breedte; }
	public int getHoogte() { return hoogte; }
	
	/**
	 * @pre | 1 <= breedte
	 * @pre | 1 <= hoogte
	 * @post | getBreedte() == breedte
	 * @post | getHoogte() == hoogte
	 * @post | getUitgaandeBogen().isEmpty()
	 * @post | getInkomendeBogen().isEmpty()
	 */
	public RechthoekigeKnoop(int breedte, int hoogte) {
		this.breedte = breedte;
		this.hoogte = hoogte;
	}
	
	/**
	 * @pre | info != null
	 * @pre | 2 <= info.length
	 * @inspects | this
	 * @mutates | info
	 * @post | info[0] == 2 * (getBreedte() + getHoogte())
	 * @post | info[1] == getBreedte() * getHoogte()
	 */
	@Override
	public void berekenInfo(int[] info) {
		info[0] = 2*(breedte + hoogte);
		info[1] = breedte * hoogte;
	}
	
	@Override
	public boolean isIsomorfMet(Knoop andere) {
		return andere instanceof RechthoekigeKnoop k && breedte == k.breedte && hoogte == k.hoogte;
	}

}
