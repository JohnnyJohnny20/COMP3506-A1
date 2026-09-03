/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 */

import uq.comp3506.a1.structures.GenomeEditor;

public class TestGenomeEditor {

    public static void main(String[] args) {
        System.out.println("Testing GenomeEditor Class...");

        GenomeEditor genomeEditor = new GenomeEditor();
        genomeEditor.insert(0,"ATGCCAT");
        System.out.println(genomeEditor);
        genomeEditor.insert(3,"TAGTAG");
        System.out.println(genomeEditor);
        System.out.println(genomeEditor.charAt(0));
        System.out.println(genomeEditor.substring(2,5));
        System.out.println("Success!");
    }

}
