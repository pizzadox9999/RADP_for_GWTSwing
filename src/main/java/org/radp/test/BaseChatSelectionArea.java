package org.radp.test;

import java.util.ArrayList;
import java.util.function.Function;

import org.radp.component.GBox;
import org.radp.component.GComponentUtil;
import org.radp.layout.GBoxLayout;
import org.radp.layout.GFlowLayout;

import de.exware.gwtswing.awt.GBorderLayout;
import de.exware.gwtswing.awt.GDimension;
import de.exware.gwtswing.awt.GFont;
import de.exware.gwtswing.awt.event.GMouseAdapter;
import de.exware.gwtswing.awt.event.GMouseEvent;
import de.exware.gwtswing.swing.GComponent;
import de.exware.gwtswing.swing.GIcon;
import de.exware.gwtswing.swing.GImageIcon;
import de.exware.gwtswing.swing.GLabel;
import de.exware.gwtswing.swing.GListModel;
import de.exware.gwtswing.swing.GPanel;
import de.exware.gwtswing.swing.GScrollPane;
import de.exware.gwtswing.swing.GSwingConstants;
import de.exware.gwtswing.swing.border.GEmptyBorder;

class Chat extends GComponent {
	private static final int ICON_SIZE = 45;
	private GIcon iconIcon;
	private GLabel nameLabel;
	private GLabel messageLabel;

	public Chat(String icon, String name, String message) {
		iconIcon = new GImageIcon(icon, ICON_SIZE, ICON_SIZE);

		GComponent textBox = new GBox(GBoxLayout.Y_AXIS);
		nameLabel = new GLabel(name);
		nameLabel.setHorizontalTextPosition(GSwingConstants.LEFT);
		nameLabel.setFont(nameLabel.getFont().deriveFont(GFont.BOLD));
		messageLabel = new GLabel(message);
		messageLabel.setHorizontalTextPosition(GSwingConstants.LEFT);
		textBox.add(nameLabel);
		textBox.add(messageLabel);

		setLayout(new GBorderLayout());
		GLabel iconLabel = new GLabel(iconIcon);
		iconLabel.getPeer().getStyle().setPadding(0);
		iconLabel.getPeer().getChild(0).getStyle().setProperty("borderRadius", "50%");
		add(iconLabel, GBorderLayout.WEST);
		add(textBox, GBorderLayout.CENTER);

		setBorder(new GEmptyBorder(3, 3, 3, 3));
	}

	public void updateMessage(String message) {
		messageLabel.setText(message);
	}

	@Override
	public GDimension getPreferredSize() {
		return new GDimension(super.getPreferredSize().width + 6, 45 + 6);
	}
}

public class BaseChatSelectionArea extends GScrollPane {
	protected SimpleEventListenerSupport chatSelectedEventListeners = new SimpleEventListenerSupport();
	protected GComponent view;

	public BaseChatSelectionArea() {
		super(null);

		setBorder(new GEmptyBorder(0, 0, 0, 0));
		view = new GBox(GBoxLayout.Y_AXIS);
		setViewportView(view);
		
		for (int i = 0; i < 100; i++) {
			view.add(generateTestComponent(i + 1));
		}
	}
	
	public void addChat(Chat chat) {
		view.add(chat);
		chat.addMouseListener(new GMouseAdapter() {
			@Override
			public void mouseClicked(GMouseEvent evt) {
				chatSelectedEventListeners.fireSimpleEventListeners();
			}
		});
	}
	
	public void addChats(ArrayList<Chat> chats) {
		for(Chat chat : chats) {
			view.add(chat);
		}
	}
	
	public void setChats(ArrayList<Chat> chats) {
		view.removeAll();
		addChats(chats);
	}
	
	public void addChatSelectedEventListener(SimpleEventListener chatSelectedEventListener) {
		chatSelectedEventListeners.addSimpleEventListener(chatSelectedEventListener);
	}

	public static final GComponent generateTestComponent(int number) {
		Chat chat = new Chat(
				"https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/A_black_image.jpg/800px-A_black_image.jpg?20201103073518",
				number + " user", "last message displayed");

		GPanel chatPanel = new GPanel(new GFlowLayout(GFlowLayout.LEFT));
		chatPanel.add(chat);

		GComponentUtil.setMaximumSize(chatPanel, new Function<GComponent, GDimension>() {
			@Override
			public GDimension apply(GComponent c) {
				return new GDimension(Integer.MAX_VALUE, c.getPreferredSize().height);
			}
		});

		return chatPanel;
	}
}