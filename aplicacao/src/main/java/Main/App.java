package Main;

import Entidades.*;
import Models.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public static void CapturarDados() {
        Looca looca = new Looca();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Máquina
        String hostName = looca.getRede().getParametros().getHostName();
        String ipv4 = String.valueOf((looca.getRede()).getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4());
        Computador computador = new Computador(hostName, ipv4);

        boolean maquinaExiste = ComputadorDAO.verificarComputador(computador);

        if (!maquinaExiste) {
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

            SistemaOperacional sistemaOperacional = new SistemaOperacional(nomeSO, tempoAtivdadeSO, hostName);

            try {
                SistemaOperacionalDAO.cadastrarSO(sistemaOperacional);
            } catch (Exception e) {
                System.out.println("Erro ao realizar o cadastro: " + e.getMessage());
            }
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

            if (memoriaDisponivel < 0.900) {
                JSONObject jsonRAM = new JSONObject();
                jsonRAM.put("text", "Máquina " + computador.getHostName() + " COM MEMÓRIA RAM SOBRECARREGADA");
                try {
                    Slack.sendMessage(jsonRAM);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            MemoriaRam memoriaRam = new MemoriaRam(memoriaUso, memoriaDisponivel, memoriaTotal, hostName);

            try {
                MemoriaRamDAO.cadastrarRAM(memoriaRam);
            } catch (Exception e) {
                System.out.println("Erro ao realizar o cadastro: " + e.getMessage());
            }

            // Disco
            Long disponivel = 0l;
            Long total = 0l;
            Long emUso = 0l;

            System.out.println("------ Disco ------");

            List<Volume> grupoDiscos = disco.getVolumes();

            for (Volume grupoDisco : grupoDiscos) {
                disponivel = grupoDisco.getDisponivel();
                total = grupoDisco.getTotal();
                emUso = total - disponivel;
            }

            Entidades.Disco disco00 = new Entidades.Disco(disponivel, total, emUso, hostName);

            try {
                DiscoDAO.cadastrarDisco(disco00);
            } catch (Exception e) {
                System.out.println("Erro ao realizar o cadastro: " + e.getMessage());
            }

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
            String nomeSO = looca.getSistema().getSistemaOperacional();

            if (nomeSO.contains("Windows")){
                if (tempCPU > 70.0) {
                    JSONObject jsonCPU = new JSONObject();
                    jsonCPU.put("text", "Temperatura da Máquina: " + computador.getHostName() + " MAIOR QUE 70º!!!");
                    try {
                        Slack.sendMessage(jsonCPU);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            Entidades.Processador cpu = new Entidades.Processador(nomeCpu, usoCPU, tempCPU, hostName);

            try {
                ProcessadorDAO.cadastrarCPU(cpu);
            } catch (Exception e) {
                System.out.println("Erro ao realizar o cadastro: " + e.getMessage());
            }

            if (nomeSO.contains("Windows")) {
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

                    Entidades.Janelas janela00 = new Janelas(idJanela, titulo, pidJanela, totalJanelas, hostName);

                    try {
                        if (titulo != null && !titulo.isEmpty()) {
                            JanelasDAO.cadastrarJanelas(janela00);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao realizar o cadastro: " + e.getMessage());
                    }
                }
            }

        };

        executor.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }
}
