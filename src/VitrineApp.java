import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class VitrineApp extends Application {
	private AnchorPane pane;
	private static Stage stage;
	private TextField txPesquisa;
	private TableView<ItensProperty> tbVitrine;
	private TableColumn<ItensProperty, String> columnProduto;
	private TableColumn<ItensProperty, Double> columnPreco;
	private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
	private static Carrinho carrinho;
	

	private void initComponentes() {
		pane = new AnchorPane();
		pane.setPrefSize(800, 600);
		pane.setStyle("-fx-background-color: #00a100");
		
		txPesquisa = new TextField();
		txPesquisa.setPromptText("Digite o item para pesquisa");
		txPesquisa.setPrefSize(200, 30);
		
		tbVitrine = new TableView<ItensProperty>();
		tbVitrine.setPrefSize(800, 550);
				
		columnProduto = new TableColumn<ItensProperty, String>();
		columnPreco = new TableColumn<ItensProperty, Double>();
		
		tbVitrine.getColumns().addAll(columnProduto, columnPreco);
		pane.getChildren().addAll(txPesquisa, tbVitrine);
		
		carrinho = new Carrinho();
		
		columnProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));
		columnProduto.setPrefWidth(700);
		
		columnPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));	
		columnPreco.setPrefWidth(100);
	}
	
	private void initItens() {
		Vitrine v = new Vitrine();
			v.addProdutos(new Produto("Bola Topper", 15.00), new Produto("Luvas Umbro", 9.00), new Produto("Camisa Esportiva", 40.00),
					new Produto("Chuteira Nike Mercurial", 199.00),	new Produto("Caneleira Topper", 10.00));
		for (Produto p : v.getProdutos())
			listItens.add(
					new ItensProperty(p.getProduto(), p.getPreco()));
		
		tbVitrine.setItems(listItens);
	}

	private ObservableList<ItensProperty> findItens(){  
	        ObservableList<ItensProperty> itensEncontrados = FXCollections.observableArrayList();  
	        for(ItensProperty itens: listItens){  
	            if(((List<ItensProperty>) itens.getProduto()).contains(txPesquisa.getText())){  
	                itensEncontrados.add(itens);  
	            }  
	        }  
	    return itensEncontrados;  
	}
	
	private void initListeners(){  
        
        txPesquisa.setOnAction(new EventHandler<ActionEvent>() {  
              
            @Override  
            public void handle(ActionEvent arg0) {  
                // TODO Auto-generated method stub  
                if(!txPesquisa.getText().equals("")){  
                      
                    tbVitrine.setItems(findItens());  
                }else{  
                    tbVitrine.setItems(listItens);  
                }  
            }  
        });  
    }

	private void initLayout() {
		txPesquisa.setLayoutX(600);
		txPesquisa.setLayoutY(5);
		tbVitrine.setLayoutX((pane.getWidth() - tbVitrine.getWidth())/2);
		tbVitrine.setLayoutY(40);
//
//		btEntrar.setLayoutX((pane.getWidth() - btEntrar.getWidth()) / 2);
//		btEntrar.setLayoutY(120);
//
//		btSair.setLayoutX((pane.getWidth() - btSair.getWidth()) / 2);
//		btSair.setLayoutY(155);
		/* Demais códigos de inicialização das coordenadas */
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponentes();
		initListeners();
		initItens();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		// Remove a opção de maximizar a tela
		stage.setResizable(false);
		// Dá um título para a tela
		stage.setTitle("Login - GolFX");
		stage.show();
		initLayout();
		VitrineApp.stage = stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
