import java.util.ArrayList;

public class DNA {

    public static ArrayList<String> dnaToCodons(String dna) {
        ArrayList<String> codons = new ArrayList<String>();

        for (int i = 0; i + 2 < dna.length(); i += 3) {
            codons.add(dna.substring(i, i + 3));
        }

        return codons;
    }

    public static String codonToAminoAcid(String codon) {

        switch (codon) {
            case "TTT":
            case "TTC":
                return "F";

            case "TTA":
            case "TTG":
            case "CTT":
            case "CTC":
            case "CTA":
            case "CTG":
                return "L";

            case "ATT":
            case "ATC":
            case "ATA":
                return "I";

            case "ATG":
                return "M";

            case "GTT":
            case "GTC":
            case "GTA":
            case "GTG":
                return "V";

            case "TCT":
            case "TCC":
            case "TCA":
            case "TCG":
            case "AGT":
            case "AGC":
                return "S";

            case "CCT":
            case "CCC":
            case "CCA":
            case "CCG":
                return "P";

            case "ACT":
            case "ACC":
            case "ACA":
            case "ACG":
                return "T";

            case "GCT":
            case "GCC":
            case "GCA":
            case "GCG":
                return "A";

            case "TAT":
            case "TAC":
                return "Y";

            case "TAA":
            case "TAG":
            case "TGA":
                return "Stop";

            case "CAT":
            case "CAC":
                return "H";

            case "CAA":
            case "CAG":
                return "Q";

            case "AAT":
            case "AAC":
                return "N";

            case "AAA":
            case "AAG":
                return "K";

            case "GAT":
            case "GAC":
                return "D";

            case "GAA":
            case "GAG":
                return "E";

            case "TGT":
            case "TGC":
                return "C";

            case "TGG":
                return "W";

            case "CGT":
            case "CGC":
            case "CGA":
            case "CGG":
            case "AGA":
            case "AGG":
                return "R";

            case "GGT":
            case "GGC":
            case "GGA":
            case "GGG":
                return "G";

            default:
                return "Unknown";
        }
    }

    public static ArrayList<String> dnaToAminoAcids(String dna) {

        ArrayList<String> codons = dnaToCodons(dna);
        ArrayList<String> aminoAcids = new ArrayList<String>();

        for (String codon : codons) {
            aminoAcids.add(codonToAminoAcid(codon));
        }

        return aminoAcids;
    }

    public static boolean isMatch(
        ArrayList<String> aminoSeq1,
        ArrayList<String> aminoSeq2) {

        return aminoSeq1.equals(aminoSeq2);
    }

    public static void main(String[] args) {

        String DNA1 = "CTGATATTGTATCCGGCCGAT";
        String DNA2 = "CTAGCCGGTGGTTATTAATAGTAAACTATTCCA";
        String DNA3 = "TTAATCCTCTACCCCGCAGAC";

        ArrayList<String> amino1 = dnaToAminoAcids(DNA1);
        ArrayList<String> amino2 = dnaToAminoAcids(DNA2);
        ArrayList<String> amino3 = dnaToAminoAcids(DNA3);

        System.out.println("DNA 1: " + amino1);
        System.out.println("DNA 2: " + amino2);
        System.out.println("DNA 3: " + amino3);

        System.out.println(
            "DNA1 and DNA2 identical: " + isMatch(amino1, amino2)
        );

        System.out.println(
            "DNA1 and DNA3 identical: " + isMatch(amino1, amino3)
        );

        System.out.println(
            "DNA2 and DNA3 identical: " + isMatch(amino2, amino3)
        );
    }
}