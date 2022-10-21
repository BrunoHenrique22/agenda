package view;

import java.awt.EventQueue;
import java.awt.JobAttributes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Agenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtFone;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor
	 */
	public Agenda() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setTitle("Agenda De Contatos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Agenda.class.getResource("/img/icone.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 345);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(302, 94, 64, 14);
		contentPane.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBounds(50, 24, 154, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 27, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 135, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(10, 94, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(50, 91, 154, 20);
		contentPane.add(txtEmail);

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(50, 132, 154, 20);
		contentPane.add(txtFone);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(335, 91, 106, 20);
		contentPane.add(txtId);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setToolTipText("Add");
		btnCreate.setBorder(null);
		btnCreate.setBackground(SystemColor.menu);
		btnCreate.setIcon(new ImageIcon(Agenda.class.getResource("/img/Add.png")));
		btnCreate.setBounds(50, 231, 64, 64);
		contentPane.add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setDefaultCapable(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Agenda.class.getResource("/img/Update.png")));
		btnUpdate.setToolTipText("Update");
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(SystemColor.menu);
		btnUpdate.setBounds(219, 231, 64, 64);
		contentPane.add(btnUpdate);

		btnRead = new JButton("");
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarContato();
			}
		});
		btnRead.setBorderPainted(false);
		btnRead.setContentAreaFilled(false);
		btnRead.setIcon(new ImageIcon(Agenda.class.getResource("/img/Search.png")));
		btnRead.setToolTipText("Procurar");
		btnRead.setBorder(null);
		btnRead.setBackground(SystemColor.menu);
		btnRead.setBounds(232, 11, 46, 47);
		contentPane.add(btnRead);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(Agenda.class.getResource("/img/Delete.png")));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setToolTipText("Delete");
		btnDelete.setBorder(null);
		btnDelete.setBackground(SystemColor.menu);
		btnDelete.setBounds(383, 231, 64, 64);
		contentPane.add(btnDelete);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(442, 10, 48, 48);
		contentPane.add(lblStatus);

		// Uso da Biblioteca
		RestrictedTextField validar = new RestrictedTextField(txtNome);
		validar.setLimit(50);
		validar.setOnlyText(true);
		validar.setAcceptSpace(true);

		RestrictedTextField validar2 = new RestrictedTextField(txtFone);
		validar2.setLimit(15);
		validar2.setOnlyNums(true);

		RestrictedTextField validar3 = new RestrictedTextField(txtEmail);
		validar3.setLimit(50);
		validar.setOnlyText(true);

		// Uso da tecla <Enter> junto a um botão (so da pra fazer com um botão)
		getRootPane().setDefaultButton(btnRead);

	}// fim do construtor

	// Criar um objeto para acessar o método conectar() da classe DAO
	DAO dao = new DAO();
	private JLabel lblStatus;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnRead;

	/**
	 * Método responsável por verificar o status da conexão com o banco
	 */

	private void status() {
		// System.out.println("Teste - Janela Ativada");
		// uso da classe Connection (JDBC) para estabelecer a conexão

		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de Conexão!!");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));

			} else {
				// System.out.println("Banco Conectado!!");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dbon.png")));
			}

			con.close(); // <---------NUNCA ESQUECER DE FECHAR A CONEXÃO ESSE É O COMANDO

		} catch (Exception e) {
			System.out.println(e);

		}

	}// FIM DO STATUS

	/**
	 * Método responsavel pela pesquisa (SELECT) de um contado no banco
	 */

	private void pesquisarContato() {

		// VALIDAÇÃO
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite O Nome Do Contato");
			txtNome.requestFocus();
		} else {

			// System.out.println("Teste Pesquisar");]
			// Iniciar com a instrução SQL
			// ? é um parametro a ser substituido
			String read = "select * from contatos where nome = ?";
			try {
				// estabelecer a conexão (abrir a geladeira , essa linha debaixo q eu abre a
				// conexão com banco de fato)
				Connection con = dao.conectar();
				// Preparação o código SQL para execução
				PreparedStatement pst = con.prepareStatement(read);
				// A linha Abaixo substitui o ? pelo conteudo da caixa de texto txtNome, o
				// numero 1 faz referencia a interrogação
				pst.setString(1, txtNome.getText());
				// obter os Dados do CONTATOS É O RESULTADO
				ResultSet rs = pst.executeQuery();
				// verificar se existe um contato cadastrado
				// rs.next() significa ter um contato corresponde ao nome pesquisado
				if (rs.next()) {
					// setar as caixas de texto com o resultado da pesquisa
					txtId.setText(rs.getString(1));
					txtFone.setText(rs.getString(3));
					txtEmail.setText(rs.getString(4));
					// habilitar botões (alterar e excluir)
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Contato inexistente");
					// setar campos e botões
					txtFone.setText(null);
					txtEmail.setText(null);
					txtFone.requestFocus();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(false);

				}

				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}
		}
	}

	// Adicionar contatos na Agenda
	void adicionarContato() {
		// validação de CAMPOS OBRIGATÓRIOS
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha O Nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha O Telefone");
			txtFone.requestFocus();

		} else {

			// System.out.println("Teste Confirmar");
			String create = "insert into contatos (nome,fone,email) values (?,?,?)";
			try {

				// abrir a conexão
				Connection con = dao.conectar();

				// Preparar a Querry (Substituição de Parâmetros)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());

				// Executar a querry e confirmar a inserção no banco
				int confirma = pst.executeUpdate();
				// System.out.println("confrima");
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato Adicionado");
					limpar();
				}
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void alterarContato() {
		// System.out.println("teste ");
		// validação de CAMPOS OBRIGATÓRIOS
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha O Nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha O Telefone");
			txtFone.requestFocus();

		} else {

		}

		// LOGICA PRINCIPAL
		String update = "update contatos set nome = ?, fone = ?, email = ? where id = ?";
		try {

			// abrir a conexão
			Connection con = dao.conectar();
			// Preparar a Querry (Substituição de Parâmetros)
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtFone.getText());
			pst.setString(3, txtEmail.getText());
			pst.setString(4, txtId.getText());

			// Executar a querry e confirmar a inserção no banco
			int confirma = pst.executeUpdate();
			// System.out.println("confrima");
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Contato Atualizado");
				limpar();
			}
			// encerrar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// METODO PARA EXCLUIR CONTATO
	private void excluirContato() {
		// System.out.println("Teste");

		// validação (confirmação)
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir Esse Contato?", "Exluir Contato!!",
				JOptionPane.YES_NO_OPTION);

		if (confirma == JOptionPane.YES_NO_OPTION) {

			String delete = "delete from contatos where id = ?";

			try {
				// abrir a conexão
				Connection con = dao.conectar();

				// Preparar a Querry (Substituição de Parâmetros)
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());

				// execurtar o comando sql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();

				if (confirmaExcluir == 1) {
					JOptionPane.showMessageDialog(null, "Contato Excluido");
					limpar();

				}
				// encerrar a conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}

		}

	}

	// Limpar Campos e resetar os botões
	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtNome.requestFocus();
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);
	}
}