package br.com.usinasantafe.pcp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcp.PCPContext;
import br.com.usinasantafe.pcp.R;
import br.com.usinasantafe.pcp.model.dao.LogProcessoDAO;

public class NomeColabVisitTercActivity extends ActivityGeneric {

    private PCPContext pcpContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_colab_visit_terc);

        pcpContext = (PCPContext) getApplication();

        TextView textViewTituloNome = findViewById(R.id.textViewTituloNome);
        TextView textViewNome = findViewById(R.id.textViewNome);
        Button buttonOkNome = findViewById(R.id.buttonOkNome);
        Button buttonCancNome =  findViewById(R.id.buttonCancNome);

        if(pcpContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcpContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){\n" +
                    "                textViewTituloNome.setText(\"NOME DO VIGIA\");\n" +
                    "                textViewNome.setText(pcpContext.getConfigCTR().getColab(pcpContext.getConfigCTR().getConfig().getMatricVigiaConfig()).getNomeColab());", getLocalClassName());
            textViewTituloNome.setText("NOME DO VIGIA");
            textViewNome.setText(pcpContext.getConfigCTR().getColab(pcpContext.getConfigCTR().getConfig().getMatricVigiaConfig()).getNomeColab());
        } else if((pcpContext.getConfigCTR().getConfig().getPosicaoTela() > 3L) && (pcpContext.getConfigCTR().getConfig().getTipoMov() == 1L)){
            LogProcessoDAO.getInstance().insertLogProcesso("} else if((pcpContext.getConfigCTR().getConfig().getPosicaoTela() < 3L) && (pcpContext.getConfigCTR().getConfig().getTipoMov() == 1L)){\n" +
                    "            textViewTituloNome.setText(\"NOME DO COLABORADOR\");\n" +
                    "            textViewNome.setText(pcpContext.getConfigCTR().getColab(pcpContext.getMovimentacaoVeicProprioCTR().getMovEquipProprioAberto().getNroMatricColabMovEquipProprio()).getNomeColab());", getLocalClassName());
            textViewTituloNome.setText("NOME DO COLABORADOR");
            textViewNome.setText(pcpContext.getConfigCTR().getColab(pcpContext.getMovVeicProprioCTR().getMovEquipProprioAberto().getNroMatricColabMovEquipProprio()).getNomeColab());
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
            if(pcpContext.getMovVeicVisitTercCTR().getMovEquipVisitTercAberto().getTipoVisitTercMovEquipVisitTerc() == 2L){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pcpContext.getMovimentacaoVeicVisTercCTR().getMovEquipVisitTercAberto().getTipoVisitTercMovEquipVisitTerc() == 1L){\n" +
                        "                textViewTituloNome.setText(\"NOME DO TERCEIRO\");\n" +
                        "                textViewNome.setText(pcpContext.getMovimentacaoVeicVisTercCTR().getTerceiroId(pcpContext.getMovimentacaoVeicVisTercCTR().getMovEquipVisitTercAberto().getIdVisitTercMovEquipVisitTerc()).getNomeTerceiro());", getLocalClassName());
                textViewTituloNome.setText("NOME DO TERCEIRO");
                textViewNome.setText(pcpContext.getMovVeicVisitTercCTR().getTerceiroId(pcpContext.getMovVeicVisitTercCTR().getMovEquipVisitTercAberto().getIdVisitTercMovEquipVisitTerc()).getNomeTerceiro());
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                textViewTituloNome.setText(\"NOME DO VISITANTE\");\n" +
                        "                textViewNome.setText(pcpContext.getMovimentacaoVeicVisTercCTR().getVisitanteId(pcpContext.getMovimentacaoVeicVisTercCTR().getMovEquipVisitTercAberto().getIdVisitTercMovEquipVisitTerc()).getNomeVisitante());", getLocalClassName());
                textViewTituloNome.setText("NOME DO VISITANTE");
                textViewNome.setText(pcpContext.getMovVeicVisitTercCTR().getVisitanteId(pcpContext.getMovVeicVisitTercCTR().getMovEquipVisitTercAberto().getIdVisitTercMovEquipVisitTerc()).getNomeVisitante());
            }
        }

        buttonOkNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkNome.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                Intent it;
                if(pcpContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcpContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){\n" +
                            "                        it = new Intent(NomeColabTercVisitActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    it = new Intent(NomeColabVisitTercActivity.this, TelaInicialActivity.class);
                } else if((pcpContext.getConfigCTR().getConfig().getPosicaoTela() > 3L) && (pcpContext.getConfigCTR().getConfig().getTipoMov() == 1L)){
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if((pcpContext.getConfigCTR().getConfig().getPosicaoTela() < 3L) && (pcpContext.getConfigCTR().getConfig().getTipoMov() == 1L)){\n" +
                            "                    it = new Intent(NomeColabVisitTercActivity.this, VeiculoUsinaActivity.class);", getLocalClassName());
                    it = new Intent(NomeColabVisitTercActivity.this, VeiculoUsinaActivity.class);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(NomeColabTercVisitActivity.this, VeiculoVisitanteTerceiroActivity.class);", getLocalClassName());
                    it = new Intent(NomeColabVisitTercActivity.this, VeiculoVisitTercResidenciaActivity.class);
                }
                startActivity(it);
                finish();
            }
        });

        buttonCancNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancNome.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                Intent it;
                if((pcpContext.getConfigCTR().getConfig().getPosicaoTela() == 3L) || (pcpContext.getConfigCTR().getConfig().getTipoMov() == 1L)){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcpContext.getConfigCTR().getConfig().getTipoMov() == 1L){\n" +
                            "                    Intent it = new Intent(NomeColabTercVisitActivity.this, ColabActivity.class);", getLocalClassName());
                    it = new Intent(NomeColabVisitTercActivity.this, ColabActivity.class);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(NomeColabTercVisitActivity.this, VisitanteTerceiroActivity.class);", getLocalClassName());
                    it = new Intent(NomeColabVisitTercActivity.this, VisitTercActivity.class);
                }
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}