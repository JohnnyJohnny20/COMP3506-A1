/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 */

import uq.comp3506.a1.structures.GenomeEditor;

public class TestGenomeEditor {

    public static void main(String[] args) {
        System.out.println("Testing GenomeEditor Class...");

        GenomeEditor genomeEditor = new GenomeEditor();
        genomeEditor.insert(0,"ATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTCATGCCATCTC");
        System.out.println(genomeEditor);
        genomeEditor.insert(3,"TAGTAG");
        System.out.println(genomeEditor);
        System.out.println(genomeEditor.charAt(0));
        System.out.println(genomeEditor.substring(2,5));
        System.out.println(genomeEditor);
        System.out.println(genomeEditor.delete(1, 5));
        System.out.println(genomeEditor);


        GenomeEditor editor = new GenomeEditor();
// Insert a large string to trigger multi-level splitting
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            largeInput.append("A");
        }

        System.out.println("Starting insert...");
        editor.insert(0, largeInput.toString()); // Your local code will hang right here!
        System.out.println("Finished insert!");

        GenomeEditor editor2 = new GenomeEditor();
        editor.insert(0, "ACGT");
// Edge case 1: Empty substring at end boundary
        System.out.println("Empty substring: '" + editor.substring(4, 4) + "'");

// Edge case 2: Delete entire contents
        editor.delete(0, 4);
        System.out.println("Length after full delete: " + editor.length());

        System.out.println("Success!");
    }

}
