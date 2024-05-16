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

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        System.out.println("""
                Seja Bem Vindo(a) a
                
                **      **  **  **   **  *******         ********  **********  *********    *********
                ** **** **  **  ***  **  **     **      **         **      **  **      **   **
                **  **  **  **  ** * **  **      **    **          **      **  *********    *****
                **      **  **  **  ***  **     **      **         **      **  **   **      **
                **      **  **  **   **  *******         ********  **********  **     **    *********
                
                """);

        Usuario.FazerLogin();
    }

    public static void CapturarDados(){
        Looca looca = new Looca();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        JanelaGrupo janelas = looca.getGrupoDeJanelas();

        // Janelas
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

            Entidades.Janelas janela00 = new Janelas(idJanela, titulo, pidJanela, totalJanelas);

            if (titulo != null && !titulo.isEmpty()){
                JanelasDAO.cadastrarJanelas(janela00);
            }
        }

        Runnable task = () -> {
            Sistema sistema = looca.getSistema();
            Memoria memoria = looca.getMemoria();
            DiscoGrupo disco = looca.getGrupoDeDiscos();
            Processador processador = looca.getProcessador();
            Temperatura temperatura = looca.getTemperatura();


            // Sistemas Operacional
            String nomeSO = sistema.getSistemaOperacional();
            Long tempoAtivdadeSO = sistema.getTempoDeAtividade() / 3600; // em horas
                System.out.println("------ Sistema Operacional ------");
                SistemaOperacional sistemaOperacional = new SistemaOperacional(nomeSO, tempoAtivdadeSO);
                SistemaOperacionalDAO.cadastrarSO(sistemaOperacional);


                // Memória RAM
            Double memoriaUso = Double.valueOf(memoria.getEmUso()) / 1e+9;
            memoriaUso = Math.round(memoriaUso * 20.0) / 20.0;
            Double memoriaDisponivel = Double.valueOf(memoria.getDisponivel()) / 1e+9;
            memoriaDisponivel = Math.round(memoriaDisponivel * 20.0) / 20.0;
            Double memoriaTotal = Double.valueOf(memoria.getTotal()) / 1e+9;
            memoriaTotal = Math.round(memoriaTotal * 20.0) / 20.0;
                System.out.println("------ Mémoria RAM ------");
                MemoriaRam memoriaRam = new MemoriaRam(memoriaUso, memoriaDisponivel, memoriaTotal);
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

                Entidades.Disco disco00 = new Entidades.Disco(tamanho, leituras, bytesLeitura, escritas, bytesEscrita, tempoTranferencia);
                DiscoDAO.cadastrarDisco(disco00);
            }

            // CPU
            String nomeCpu = processador.getNome();
            Double usoCPU = processador.getUso();
            Double tempCPU = temperatura.getTemperatura();
                System.out.println("------ CPU ------");
            Entidades.Processador cpu = new Entidades.Processador(nomeCpu, usoCPU, tempCPU);
            ProcessadorDAO.cadastrarCPU(cpu);

        };


        executor.scheduleAtFixedRate(task, 0, 20, TimeUnit.SECONDS);

    }
}
