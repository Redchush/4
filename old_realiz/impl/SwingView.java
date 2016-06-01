package old_realiz.impl;

import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;
import catalog.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 30.05.2016.
 */
public class SwingView extends JFrame implements View {

    JPanel controlPanel;
    public SwingView() {
        super("NewsChooser");
        setSize(800, 800);
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Закрыть");
        JMenuItem item = new JMenuItem("Закрыть");
        menu.add(item);
        bar.add(menu);
        setJMenuBar(bar);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLUE);

        String[] items = {
                "Категория",
                "Подкатегория",
                "Название содержит",
                "Новость содержит"
        };

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();

            }
        };
        Container content = getContentPane();
//        LayoutManager manager = new BoxLayout(content, BoxLayout.Y_AXIS);
//        content.setLayout(manager);

        JTextField textField = new JTextField();
        textField.setVisible(true);
        textField.setBounds(50, 100, 200, 40);

        JComboBox comboBox = new JComboBox(items);
        comboBox.setBounds(50, 140 ,200,40);

        comboBox.setAlignmentX(LEFT_ALIGNMENT);
        comboBox.addActionListener(actionListener);

        comboBox.setVisible(true);
        comboBox.setOpaque(true);
        JButton bottom = new JButton("Подтвердить");
        bottom.setBounds(50, 180, 200, 40);
        bottom.setVisible(true);

        add(comboBox);
        add(textField);
        add(bottom);



//        controlPanel.setLayout(new FlowLayout());
//        controlPanel.setSize(300, 800);
//        controlPanel.setOpaque(true);
//
//        controlPanel.setName("Панель управления");
//        controlPanel.setBackground(Color.BLACK);
//        controlPanel.setVisible(true);
//        controlPanel.setLayout(new BorderLayout());
//
//
//        add(controlPanel, BorderLayout.CENTER);
        setVisible(true);

    }


    @Override
    public Request doUserAction() {

        Request request = new Request();
        request.setCommandName("SAVE_NEW_NEWS");


        return request;
    }

    @Override
    public void printAnswer(Response response) {

    }

    @Override
    public void printAnswer(String message) {

    }
}
