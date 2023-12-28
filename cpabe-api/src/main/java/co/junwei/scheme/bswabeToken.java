package co.junwei.scheme;

import co.junwei.digitalsignature.Dsa;
import co.junwei.digitalsignature.SignatureObject;
import it.unisa.dia.gas.jpbc.Element;

public class bswabeToken {
	public Element tok1; /* G_2 */
	public Element tok2; /* G_2 */
	public Element tok3; /* G_2 */
	public Element tok4; /* G_2 */
	public Element tok5; /* G_T */
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
		ans=ans&check(tok1)&check(tok2)&check(tok3)&check(tok4)&check(tok5);
		return ans;
	}
}

