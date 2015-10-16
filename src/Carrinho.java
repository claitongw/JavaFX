import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private static List<Produto> produtos = new ArrayList<Produto>();

	public void addProdutos(Produto... ps) {
		for (Produto p : ps)
			produtos.add(p);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}