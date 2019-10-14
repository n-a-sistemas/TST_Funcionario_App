package com.example.tstfuncionario_30.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tstfuncionario_30.R;

public class SlideshowFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_view_regras);

        textView.setText("Regras do jogo\n" +
                "\tCada funcionário iniciará a temporada com 1000 pontos.\n" +
                "\n" +
                "\tAo longo da temporada, essa pontuação poderá ser alterada dependendo das boas e/ou más atuações do funcionário dentro da empresa.\n" +
                " Caso o funcionário contribua na segurança da empresa, o mesmo poderá receber bonificações sendo somada a pontuação total,\n" +
                " caso contrário o funcionário perderá uma quantidade de pontos dependendo de cada infração cometida.\n" +
                "\n" +
                "\tO jogo terá dois tipos de premiações. A semestral, que será uma premiação coletiva caso a empresa consiga ficar 6 meses sem acidentes.\n" +
                " A premiação será uma comemoração com um churrascão. Caso ocorra algum acidente, a comemoração da temporada será cancelada, voltando a valer somente no próximo semestre.\n" +
                "\tA segunda premiação será trimestral, onde os três primeiros do ranking receberão uma premiação individual.\n" +
                " Para o primeiro colocado, receberá uma bonificação de R$1000,00.\n" +
                " Para o segundo colocado, receberá uma bonificação de R$500,00.\n" +
                " Para o terceiro colocado, receberá uma bonificação de R$250,00.\n" +
                "\tAs bonificações serão acrescentadas no salário do mês seguinte ao término da temporada.\n" +
                "\n" +
                "\tAo relatar problemas técnicos na empresa, o funcionário receberá 20 pontos;\n" +
                "\n" +
                "\tAo trocar os EPIs antes da data de vencimento o funcionário receberá 15 pontos;\n" +
                "\n" +
                "\tSe o funcionário não estiver equipado corretamente com todos os seus EPIs, o mesmo perderá 30 pontos;\n" +
                "\n" +
                "\tAo chegar atrasado sem justificativa, o funcionário perderá 15 pontos;\n" +
                "\n" +
                "\tCaso o funcionário cause conflitos dentro da empresa perderá 40 pontos;\n" +
                "\n" +
                "\tAo final de cada dia, todos os funcionários receberão 5 pontos.");

        return root;
    }
}