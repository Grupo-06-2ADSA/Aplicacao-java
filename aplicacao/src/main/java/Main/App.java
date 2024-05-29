package Main;

import Entidades.*;
import Models.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("""       
                         
                ███╗   ███╗██╗███╗   ██╗██████╗      ██████╗ ██████╗ ██████╗ ███████╗
                ████╗ ████║██║████╗  ██║██╔══██╗    ██╔════╝██╔═══██╗██╔══██╗██╔════╝
                ██╔████╔██║██║██╔██╗ ██║██║  ██║    ██║     ██║   ██║██████╔╝█████╗ \s
                ██║╚██╔╝██║██║██║╚██╗██║██║  ██║    ██║     ██║   ██║██╔══██╗██╔══╝ \s
                ██║ ╚═╝ ██║██║██║ ╚████║██████╔╝    ╚██████╗╚██████╔╝██║  ██║███████╗
                ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝      ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
                                                                                    \s               
                """);

        Usuario.FazerLogin();
    }

    public static void CapturarDados (){
        Looca looca = new Looca();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Máquina
        String hostName = looca.getRede().getParametros().getHostName();
        String ipv4 = String.valueOf((looca.getRede()).getGrupoDeInterfaces().getInterfaces().get(4).getEnderecoIpv4());
        Computador computador = new Computador(hostName, ipv4);

        boolean maquinaExiste = ComputadorDAO.verificarComputador(computador);
        Integer fkMaquina = ComputadorDAO.buscarIdMaquina(computador);

        if (!maquinaExiste){
            ComputadorDAO.cadastrarComputador(computador);
        }

        Runnable taskSO = () -> {
            // Sistemas Operacional
            Sistema sistema = looca.getSistema();
            String nomeSO = sistema.getSistemaOperacional();
            Long tempoAtivdadeSO = sistema.getTempoDeAtividade() / 3600; // em horas
            System.out.println("------ Sistema Operacional ------");

//            if (tempoAtivdadeSO > 730) {
//                JSONObject jsonSO = new JSONObject();
//                jsonSO.put("text", "Máquina " + computador.getHostName() + " NECESSITA REINICIAR!!!");
//                try {
//                    Slack.sendMessage(jsonSO);
//                } catch (IOException | InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            SistemaOperacional sistemaOperacional = new SistemaOperacional(nomeSO, tempoAtivdadeSO, fkMaquina);
            SistemaOperacionalDAO.cadastrarSO(sistemaOperacional);
        };

        executor.scheduleAtFixedRate(taskSO, 0, 2, TimeUnit.HOURS);


        Runnable task = () -> {
        Memoria memoria = looca.getMemoria();
        DiscoGrupo disco = looca.getGrupoDeDiscos();
        Processador processador = looca.getProcessador();
        Temperatura temperatura = looca.getTemperatura();

        // Memória RAM
        Double memoriaUso = Double.valueOf(memoria.getEmUso()) / 1e+9;
        memoriaUso = Math.round(memoriaUso * 20.0) / 20.0;
        Double memoriaDisponivel = Double.valueOf(memoria.getDisponivel()) / 1e+9;
        memoriaDisponivel = Math.round(memoriaDisponivel * 20.0) / 20.0;
        Double memoriaTotal = Double.valueOf(memoria.getTotal()) / 1e+9;
        memoriaTotal = Math.round(memoriaTotal * 20.0) / 20.0;
            System.out.println("------ Mémoria RAM ------");

            if (memoriaDisponivel < 0.900){
                JSONObject jsonRAM = new JSONObject();
                jsonRAM.put("text", "Máquina "+ computador.getHostName()+ " COM MEMÓRIA RAM SOBRECARREGADA");
                try {
                    Slack.sendMessage(jsonRAM);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        MemoriaRam memoriaRam = new MemoriaRam(memoriaUso, memoriaDisponivel, memoriaTotal, fkMaquina);
        MemoriaRamDAO.cadastrarRAM(memoriaRam);


            // Disco
        List<Disco> discos = disco.getDiscos();
        Double tamanho = 0.0;
        Double leituras = 0.0;
        Double bytesLeitura = 0.0;
        Double escritas = 0.0;
        Double bytesEscrita = 0.0;
        Long tempoTranferencia = 0l;
        System.out.println("------ Disco ------");

        for (Disco disco1 : discos) {
            tamanho = disco1.getTamanho() / 1e+9;
            leituras = Double.valueOf(disco1.getLeituras());
            bytesLeitura = Double.valueOf(disco1.getBytesDeLeitura());
            escritas = Double.valueOf(disco1.getEscritas());
            bytesEscrita = Double.valueOf(disco1.getBytesDeEscritas());
            tempoTranferencia = disco1.getTempoDeTransferencia();
        }

        Entidades.Disco disco00 = new Entidades.Disco(tamanho, leituras, bytesLeitura, escritas, bytesEscrita, tempoTranferencia, fkMaquina);
        DiscoDAO.cadastrarDisco(disco00);


//            JSONObject jsonREDE = new JSONObject();
//            jsonREDE.put("text", "O usuário "+Usuario.getEmail()+ " Realizou login");
//            try {
//                Slack.sendMessage(jsonREDE);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        // CPU
        String nomeCpu = processador.getNome();
        Double usoCPU = processador.getUso();
        Double tempCPU = temperatura.getTemperatura();
        System.out.println("------ CPU ------");

            if (tempCPU > 70.0){
                JSONObject jsonCPU = new JSONObject();
                jsonCPU.put("text", "Temperatura da Máquina: " + computador.getHostName() + " MAIOR QUE 70º!!!");
                try {
                    Slack.sendMessage(jsonCPU);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        Entidades.Processador cpu = new Entidades.Processador(nomeCpu, usoCPU, tempCPU, fkMaquina);
        ProcessadorDAO.cadastrarCPU(cpu);

        // JanelasJanelaGrupo
        JanelaGrupo janelas = looca.getGrupoDeJanelas();

        List<Janela> janela1 = janelas.getJanelas();
        Long idJanela;
        String titulo;
        Long pidJanela;

        Integer totalJanelas = janelas.getTotalJanelas();
        System.out.println("------ Janelas ------");

        for (Janela janela : janela1) {
            idJanela = janela.getJanelaId();
            titulo = janela.getTitulo();
            pidJanela = janela.getPid();

            Entidades.Janelas janela00 = new Janelas(idJanela, titulo, pidJanela, totalJanelas, fkMaquina);

                if (titulo != null && !titulo.isEmpty()){
                    JanelasDAO.cadastrarJanelas(janela00);
                }
            }
        };

        executor.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }
}
