import Entidades.Computador;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeParametros;
import com.github.britooo.looca.api.group.sistema.Sistema;
import Insercao.ComputadorDAO;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Looca looca = new Looca();

        Sistema sistema = looca.getSistema();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo disco = looca.getGrupoDeDiscos();
        Rede rede = looca.getRede();

        Computador computador = new Computador();

        String SO = sistema.getSistemaOperacional();
        Double memoriaUso = Double.valueOf(memoria.getEmUso());
        Double discoUso = Double.valueOf(disco.getTamanhoTotal() / 10000000.);
        RedeParametros redeParametros = rede.getParametros();

        computador.setSistemaOperacional(SO);
        computador.setDiscoUso(discoUso);
        computador.setMemoriaUso(memoriaUso);

        ComputadorDAO.cadastrarComputador(computador);

        System.out.println(sistema);
        System.out.println(memoria);
        System.out.println(redeParametros);
        System.out.println(rede.getGrupoDeInterfaces().getInterfaces().get(4));
        System.out.println(looca.getProcessador());
        System.out.println(looca.getTemperatura());

        List<Disco> discos = disco.getDiscos();

        for (Disco disco1 : discos) {
            System.out.println(disco1);
        }

        System.out.println();
    }
}
