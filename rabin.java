import java.lang.*;
import java.math.BigInteger;
public class rabin {
	//b sayısının mod a'ya göre tersini bulan fonksiyon
	public static BigInteger oklit(BigInteger a,BigInteger b){
		BigInteger x1 = BigInteger.valueOf(1);
		BigInteger x2 = BigInteger.valueOf(0);
		BigInteger x3 = a;
		BigInteger y1 = BigInteger.valueOf(0);
		BigInteger y2 = BigInteger.valueOf(1);
		BigInteger y3 = b;
		BigInteger q = BigInteger.valueOf(0);
		int i = 1;
		BigInteger t1 = BigInteger.valueOf(0);
		BigInteger t2 = BigInteger.valueOf(0);
		BigInteger t3 = BigInteger.valueOf(0);
		do{
			if(i == 1){
				q = x3.divide(y3);
				t1 = x1.subtract((q.multiply(y1)));
				t2 = x2.subtract((q.multiply(y2)));
				t3 = x3.subtract((q.multiply(y3)));
			}
			else{
				x1 = y1; x2 = y2; x3 = y3;
                y1 = t1; y2 = t2; y3 = t3;
                q = x3.divide(y3);
                t1 = x1.subtract((q.multiply(y1)));
				t2 = x2.subtract((q.multiply(y2)));
				t3 = x3.subtract((q.multiply(y3)));
			}
			i++;
			if (y3.intValue() == 0)
            {
                break;
            }
		}while (y3.intValue() != 1);
		if (y3.intValue() == 0)
        {
            System.out.println("Sayinin tersi yoktur!!!!");
            return y2;
        }
        else
        {
        	return y2;
        }
	}
	//Gönderilen p ve q (kapalı anahtar) değerlerine göre açık anahtarı bulan fonksiyon
	public static BigInteger anahtar(int p,int q){
		BigInteger n = BigInteger.valueOf(p*q);
		return n;
	}
	//Şifrelediğimiz sayıyı daha sonra 4 kök arasından seçebilmemiz için sayının son 6 bitini tekrar ettiren fonksiyon 
	public static BigInteger altiBitTekrarla(int m){
		String mString = Integer.toBinaryString(m);
		if(mString.length() < 6){
			mString = "000000" + mString;
		}
		mString = mString + mString.substring(mString.length()-6);
		Long mL = Long.parseLong(mString, 2);
		BigInteger mBig = BigInteger.valueOf(mL);
		return mBig;
	}
	//Gönderilen, m sayısı ve açık anahtar ile şifreleme yapan fonksiyon
	public static BigInteger sifrele(BigInteger n,BigInteger mBig){
		BigInteger c = mBig.modPow(BigInteger.valueOf(2), n);
		return c;
	}
	//Gönderilen, şifrelenmiş c sayısı ve kapalı anahtar ile deşifreleme yapan fonksiyon
	public static BigInteger[] desifrele(BigInteger c,int pInt,int qInt){
		BigInteger p = BigInteger.valueOf(pInt);
		BigInteger q = BigInteger.valueOf(qInt);
		BigInteger n = p.multiply(q);
		BigInteger yp = oklit(q,p);
		BigInteger yq = oklit(p,q);
        BigInteger mp = (c.pow((p.add(BigInteger.valueOf(1)).divide(BigInteger.valueOf(4))).intValue())).mod(p);
        BigInteger mq = (c.pow((q.add(BigInteger.valueOf(1)).divide(BigInteger.valueOf(4))).intValue())).mod(q);
		BigInteger r = ((yp.multiply(p.multiply(mq))).add((yq.multiply(q.multiply(mp))))).mod(n);
		BigInteger rr = n.subtract(r);
		BigInteger s = ((yp.multiply(p.multiply(mq))).subtract((yq.multiply(q.multiply(mp))))).mod(n);
		BigInteger ss = n.subtract(s);
		return new BigInteger[]{r,rr,s,ss};
	}
	//Deşifrelemen sonra bulunan 4 kökten son 6 biti tekrar eden sayıyı bulan fonksiyon
	//(Son 6 biti tekrar eden sayı,4 kök arasında şifrelediğimiz sayıdır)
	public static int bul(BigInteger[] dortkok){
		int r,rr,s,ss;
		r = dortkok[0].intValue();
		String rString = Integer.toBinaryString(r);
		rr = dortkok[1].intValue();
		String rrString = Integer.toBinaryString(rr);
		s = dortkok[2].intValue();
		String sString = Integer.toBinaryString(s);
		ss = dortkok[3].intValue();
		String ssString = Integer.toBinaryString(ss);
		if(rString.regionMatches(rString.length()-12, rString, rString.length()-6, 6)){
			StringBuffer rBuffer = new StringBuffer();
			rBuffer.append(rString);
			rBuffer.delete(rBuffer.length()-6, rBuffer.length());
			rString = rBuffer.toString();
			r = Integer.parseInt(rString, 2);
			return r;
		}
		if(rrString.regionMatches(rrString.length()-12, rrString, rrString.length()-6, 6)){
			StringBuffer rrBuffer = new StringBuffer();
			rrBuffer.append(rrString);
			rrBuffer.delete(rrBuffer.length()-6, rrBuffer.length());
			rrString = rrBuffer.toString();
			rr = Integer.parseInt(rrString, 2);
			return rr;
		}
		if(sString.regionMatches(sString.length()-12, sString, sString.length()-6, 6)){
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(sString);
			sBuffer.delete(sBuffer.length()-6, sBuffer.length());
			sString = sBuffer.toString();
			s = Integer.parseInt(sString, 2);
			return s;
		}
		if(ssString.regionMatches(ssString.length()-12, ssString, ssString.length()-6, 6)){
			StringBuffer ssBuffer = new StringBuffer();
			ssBuffer.append(ssString);
			ssBuffer.delete(ssBuffer.length()-6, ssBuffer.length());
			ssString = ssBuffer.toString();
			ss = Integer.parseInt(ssString, 2);
			return ss;
		}
		System.out.println("Hata!!!");
		return 0;
	}
	public static void main(String[] args) {
		BigInteger n = anahtar(10079,66047);  // p ≡ q ≡ 3 (mod 4) kuralına uygun büyük 2 asal sayı kapalı anahtar olarak belirlenir
		int m;
		BigInteger cBig,mBig;
		m = 1265434;  //Şifrelenecek sayı
		mBig = altiBitTekrarla(m);
		cBig = sifrele(n,mBig);
		BigInteger[] dortkok = desifrele(cBig,10079,66047);
		m = bul(dortkok);
		System.out.println(m);
	}
}