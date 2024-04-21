package Principais;

import Entidades.Computador;
import Entidades.MemoriaRam;
import Entidades.SistemaOperacional;
import Models.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Looca looca = new Looca();

        Sistema sistema = looca.getSistema();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo disco = looca.getGrupoDeDiscos();
        Processador processador = looca.getProcessador();
        Rede rede = looca.getRede();

        // Sistemas Operacional
        String nomeSO = sistema.getSistemaOperacional();
        Long tempoAtivdadeSO = sistema.getTempoDeAtividade() / 3600; // em horas

        // Memória RAM
        Double memoriaUso = Double.valueOf(memoria.getEmUso()) / 1e+9;
        memoriaUso = Math.round(memoriaUso * 20.0) / 20.0;
        Double memoriaDisponivel = Double.valueOf(memoria.getDisponivel()) / 1e+9;
        memoriaDisponivel = Math.round(memoriaDisponivel * 20.0) / 20.0;
        Double memoriaTotal = Double.valueOf(memoria.getTotal()) / 1e+9;
        memoriaTotal = Math.round(memoriaTotal * 20.0) / 20.0;

        // Disco
        List<Disco> discos = disco.getDiscos();
        Double tamanho = 0.0;
        Double leituras = 0.0;
        Double bytesLeitura = 0.0;
        Double escritas = 0.0;
        Double bytesEscrita = 0.0;
        Long tempoTranferencia = 0l;

        for (Disco disco1 : discos) {
            tamanho = disco1.getTamanho() / 1e+9;
            leituras = Double.valueOf(disco1.getLeituras());
            bytesLeitura = Double.valueOf(disco1.getBytesDeLeitura());
            escritas = Double.valueOf(disco1.getEscritas());
            bytesEscrita = Double.valueOf(disco1.getBytesDeEscritas());
            tempoTranferencia = disco1.getTempoDeTransferencia();
        }

        // Rede
        String nomeRede = String.valueOf(rede.getGrupoDeInterfaces().getInterfaces().get(4).getNomeExibicao());
        Long bytesRecebidos = rede.getGrupoDeInterfaces().getInterfaces().get(4).getBytesRecebidos();
        Long bytesEnviados = rede.getGrupoDeInterfaces().getInterfaces().get(4).getBytesEnviados();
        Long pacotesRecebidos = rede.getGrupoDeInterfaces().getInterfaces().get(4).getPacotesRecebidos();
        Long pacotesEnviados = rede.getGrupoDeInterfaces().getInterfaces().get(4).getPacotesEnviados();

        // CPU
        String nomeCpu = processador.getNome();
        Double usoCPU = processador.getUso();


        // Máquina
        String hostName = rede.getParametros().getHostName();
        String ipv4 = String.valueOf((rede.getGrupoDeInterfaces().getInterfaces().get(4).getEnderecoIpv4()));

        Computador computador = new Computador(hostName, ipv4);
        SistemaOperacional sistemaOperacional = new SistemaOperacional(nomeSO, tempoAtivdadeSO);
        Entidades.Disco disco00 = new Entidades.Disco(tamanho, leituras, bytesLeitura, escritas, bytesEscrita, tempoTranferencia);
        MemoriaRam memoriaRam = new MemoriaRam(memoriaUso, memoriaDisponivel, memoriaTotal);
        Entidades.Rede rede00 = new Entidades.Rede(nomeRede, bytesRecebidos, bytesEnviados, pacotesRecebidos, pacotesEnviados);
        Entidades.Processador cpu = new Entidades.Processador(nomeCpu, usoCPU);

        ComputadorDAO.cadastrarComputador(computador);
        SistemaOperacionalDAO.cadastrarSO(sistemaOperacional);
        DiscoDAO.cadastrarDisco(disco00);
        MemoriaRamDAO.cadastrarRAM(memoriaRam);
        RedeDAO.cadastrarRede(rede00);
        ProcessadorDAO.cadastrarCPU(cpu);
    }
}
