import add.*;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VigenereScreens {
    public static void mainScreen(JFrame f) {
        GridBagConstraints gdc = Initializer.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel label = new JLabel("Select between encoding and decoding your text");
        label.setOpaque(true);
        f.add(label, gdc);

        gdc.anchor = GridBagConstraints.WEST;
        gdc.gridy++;
        JButton codeButton = new JButton("Code");
        codeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VigenereScreens.secondScreen(f, "Code");
            }
        });
        f.add(codeButton, gdc);

        gdc.anchor = GridBagConstraints.EAST;
        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VigenereScreens.secondScreen(f, "Decode");
            }
        });
        f.add(decodeButton, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void secondScreen(JFrame f, String chosen) {
        GridBagConstraints gdc = Initializer.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel keyLabel = new JLabel(((char)0x2193) + " Enter your key here " + ((char)0x2193));
        keyLabel.setOpaque(true);
        f.add(keyLabel, gdc);
        gdc.gridx += 1;

        JLabel keyArrLabel = new JLabel(((char)0x2193) + " Chose your language here " + ((char)0x2193));
        keyArrLabel.setOpaque(true);
        f.add(keyArrLabel, gdc);
        gdc.gridx += 1;

        JLabel textLabel = new JLabel(((char)0x2193) + " Enter your text here " + ((char)0x2193));
        textLabel.setOpaque(true);
        f.add(textLabel, gdc);
        gdc.gridx = 0;
        gdc.gridy++;

        JTextArea inputKey = new JTextArea(1, 20);
        inputKey.setEditable(true);
        inputKey.setBackground(Color.LIGHT_GRAY);
        f.add(inputKey, gdc);
        gdc.gridx += 1;

        String[] languages = {"", "English", "Ukraine"};
        JComboBox language = new JComboBox(languages);
        language.setBackground(Color.LIGHT_GRAY);
        f.add(language, gdc);
        gdc.gridx += 1;

        JTextArea inputText = new JTextArea(1, 20);
        inputText.setEditable(true);
        inputText.setBackground(Color.LIGHT_GRAY);
        f.add(inputText, gdc);
        gdc.gridx = 0;
        gdc.gridy++;

        JTextPane exampleKey = new JTextPane();
        exampleKey.setText("Example: \"Ключ\"");
        exampleKey.setEditable(false);
        f.add(exampleKey, gdc);
        gdc.gridx += 2;

        JTextPane exampleText = new JTextPane();
        exampleText.setText("Example: \"Криптосистема\"");
        exampleText.setEditable(false);
        f.add(exampleText, gdc);
        gdc.gridx--;
        gdc.gridy++;

        //symbols limits
        inputKey.addKeyListener(new TextConsumer(inputKey, 40));
        inputText.addKeyListener(new TextConsumer(inputText, 40));
        //symbols limits

        JButton button = new JButton(chosen);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = switch (chosen) {
                    case ("Code") -> VigenereCipher.code(inputText.getText(),
                            inputKey.getText(), language.getItemAt(language.getSelectedIndex()).toString());
                    case ("Decode") -> VigenereCipher.decode(inputText.getText(),
                            inputKey.getText(), language.getItemAt(language.getSelectedIndex()).toString());
                    default -> throw new IllegalStateException("Unexpected value: " + chosen);
                };
                VigenereScreens.thirdScreen(f, str);
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void thirdScreen(JFrame f, String output) {
        GridBagConstraints gdc = Initializer.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JTextArea textArea = new JTextArea(1, 20);
        textArea.setText(output);
        textArea.setEditable(false);
        f.add(textArea, gdc);
        gdc.gridy++;

        JButton button = new JButton("Go to main screen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VigenereScreens.mainScreen(f);
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }
}