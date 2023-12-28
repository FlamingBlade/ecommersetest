package co.junwei.scheme;

import co.junwei.digitalsignature.Dsa;
import co.junwei.digitalsignature.SignatureObject;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class bswabePub{
	/*
	 * A public key
	 */
	public String pairingDesc;
	public Pairing p;		
	public Element g1;				/* G_1*/
	public Element g_a;				/* G_1 */
	public Element g_b;				/* G_1 */
	public Element g_c;				/* G_1 */
	public Element []u ;            /* G_1*/
	public Element []y ;            /* G_T*/
	public Element g2;			    /* G_2 */
	boolean check(Element e) throws Exception
	{
		String inputString=e.toString();
		SignatureObject temp;
		inputString=e.toString();
		temp=Dsa.GenerateSignature(inputString);
      	boolean test=Dsa.VerifySignature(temp,inputString);
		return test;
	}
	public boolean checkSignature()throws Exception
	{
		boolean ans=true;
		ans=ans&check(g1)&check(g_a)&check(g_b)&check(g_c)&check(g2);
		for(Element e:u)
		ans=ans&check(e);
		for(Element e:y)
		ans=ans&check(e);
		return ans;
	}
	
	
	
			
	
	//public Element gp;			/* G_2 */
	//public Element g_hat_alpha;	/* G_T */
}
