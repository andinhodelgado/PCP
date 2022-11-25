package br.com.usinasantafe.pcp.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcp.model.bean.variaveis.MovEquipVisitTercBean;
import br.com.usinasantafe.pcp.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcp.util.Tempo;

public class MovEquipVisitTercDAO {

    public MovEquipVisitTercDAO() {
    }

    public boolean verMovEquipVisitTercFechado(){
        List<MovEquipVisitTercBean> movEquipVisitTercList = movEquipVisitTercFechadoList();
        boolean ret = (movEquipVisitTercList.size() > 0);
        movEquipVisitTercList.clear();
        return ret;
    }

    public void abrirMovEquipVisitTerc(Long tipoMov){
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        movEquipVisitTercBean.setTipoMovEquipVisitTerc(tipoMov);
        movEquipVisitTercBean.setStatusMovEquipVisitTerc(1L);
        if(tipoMov == 1L){
            movEquipVisitTercBean.setStatusEntradaSaidaMovEquipVisitTerc(1L);
        } else {
            movEquipVisitTercBean.setStatusEntradaSaidaMovEquipVisitTerc(2L);
        }
        movEquipVisitTercBean.insert();
    }

    public void fecharMovEquipVisitTerc(Long nroMatricVigia, String observacao, Long posicaoListaMov){
        MovEquipVisitTercBean movEquipVisitTercBean = getMovEquipVisitTercAberto();
        if(movEquipVisitTercBean.getTipoMovEquipVisitTerc() == 2L){
            List<MovEquipVisitTercBean> movEquipVisitTercList = movEquipVisitTercEntradaList();
            MovEquipVisitTercBean movEquipVisitTercEntradaBean =  movEquipVisitTercList.get(posicaoListaMov.intValue());
            movEquipVisitTercBean.setTipoVisitTercMovEquipVisitTerc(movEquipVisitTercEntradaBean.getTipoVisitTercMovEquipVisitTerc());
            movEquipVisitTercBean.setIdVisitTercMovEquipVisitTerc(movEquipVisitTercEntradaBean.getIdVisitTercMovEquipVisitTerc());
            movEquipVisitTercBean.setVeiculoMovEquipVisitTerc(movEquipVisitTercEntradaBean.getVeiculoMovEquipVisitTerc());
            movEquipVisitTercBean.setPlacaMovEquipVisitTerc(movEquipVisitTercEntradaBean.getPlacaMovEquipVisitTerc());
            movEquipVisitTercEntradaBean.setStatusEntradaSaidaMovEquipVisitTerc(2L);
            movEquipVisitTercEntradaBean.update();
            movEquipVisitTercList.clear();
        }
        movEquipVisitTercBean.setNroMatricVigiaMovEquipVisitTerc(nroMatricVigia);
        movEquipVisitTercBean.setObservacaoMovEquipVisitTerc(observacao);
        Long dthr = Tempo.getInstance().dthrAtualLong();
        movEquipVisitTercBean.setDthrLongMovEquipVisitTerc(dthr);
        movEquipVisitTercBean.setDthrMovEquipVisitTerc(Tempo.getInstance().dthrLongToString(dthr));
        movEquipVisitTercBean.setStatusMovEquipVisitTerc(2L);
        movEquipVisitTercBean.update();
    }

    public void updateEquipVisitTercEnvio(ArrayList<Long> idMovEquipVisitTercArrayList){

        List<MovEquipVisitTercBean> movEquipVisitTercList = movEquipVisitTercEntradaList(idMovEquipVisitTercArrayList);

        for (MovEquipVisitTercBean movEquipVisitTercBean : movEquipVisitTercList) {
            movEquipVisitTercBean.setStatusMovEquipVisitTerc(3L);
            movEquipVisitTercBean.update();
        }

        movEquipVisitTercList.clear();
        idMovEquipVisitTercArrayList.clear();

    }

    public void deleteMovEquipVisitTerc(Long idMovEquipVisitTerc){
        MovEquipVisitTercBean movEquipVisitTercBean = getMovEquipVisitTercId(idMovEquipVisitTerc);
        movEquipVisitTercBean.delete();
    }

    public ArrayList<Long> idMovEquipVisitTercExcluirArrayList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqMovEnviado());

        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        List<MovEquipVisitTercBean> movEquipVisitTercList = movEquipVisitTercBean.get(pesqArrayList);

        ArrayList<Long> idMovEquipVisitTercList = new ArrayList<>();
        for (MovEquipVisitTercBean movEquipVisitTercBeanBD : movEquipVisitTercList) {
            if(movEquipVisitTercBeanBD.getDthrLongMovEquipVisitTerc() < Tempo.getInstance().dthrLongDiaMenos(15)) {
                idMovEquipVisitTercList.add(movEquipVisitTercBeanBD.getIdMovEquipVisitTerc());
            }
        }

        movEquipVisitTercList.clear();
        return idMovEquipVisitTercList;

    }

    public MovEquipVisitTercBean getMovEquipVisitTercAberto(){
        List<MovEquipVisitTercBean> movEquipVisitTercList = movEquipVisitTercAbertoList();
        MovEquipVisitTercBean movEquipVisitTercBean = movEquipVisitTercList.get(0);
        movEquipVisitTercList.clear();
        return movEquipVisitTercBean;
    }

    public  MovEquipVisitTercBean getMovEquipVisitTercId(Long idMovEquipVisitTerc){
        List<MovEquipVisitTercBean> movEquipVisitTercList = movEquipVisitTercAbertoId(idMovEquipVisitTerc);
        MovEquipVisitTercBean movEquipVisitTercBean = movEquipVisitTercList.get(0);
        movEquipVisitTercList.clear();
        return movEquipVisitTercBean;
    }

    public void setTipoVisitTerc(Long tipoVisitTerc){
        MovEquipVisitTercBean movEquipVisitTercBean = getMovEquipVisitTercAberto();
        movEquipVisitTercBean.setTipoVisitTercMovEquipVisitTerc(tipoVisitTerc);
        movEquipVisitTercBean.update();
    }

    public void setIdVisitTerc(Long idVisitTerc){
        MovEquipVisitTercBean movEquipVisitTercBean = getMovEquipVisitTercAberto();
        movEquipVisitTercBean.setIdVisitTercMovEquipVisitTerc(idVisitTerc);
        movEquipVisitTercBean.update();
    }

    public void setVeiculoVisitTerc(String veiculo){
        MovEquipVisitTercBean movEquipVisitTercBean = getMovEquipVisitTercAberto();
        movEquipVisitTercBean.setVeiculoMovEquipVisitTerc(veiculo);
        movEquipVisitTercBean.update();
    }

    public void setPlacaVisitTerc(String placa){
        MovEquipVisitTercBean movEquipVisitTercBean = getMovEquipVisitTercAberto();
        movEquipVisitTercBean.setPlacaMovEquipVisitTerc(placa);
        movEquipVisitTercBean.update();
    }

    public void setDestinoVisitTerc(String destino){
        MovEquipVisitTercBean movEquipVisitTercBean = getMovEquipVisitTercAberto();
        movEquipVisitTercBean.setDestinoMovEquipVisitTerc(destino);
        movEquipVisitTercBean.update();
    }

    public List<MovEquipVisitTercBean> movEquipVisitTercAbertoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqMovAberto());
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        return movEquipVisitTercBean.get(pesqArrayList);
    }

    public List<MovEquipVisitTercBean> movEquipVisitTercFechadoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqMovFechado());
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        return movEquipVisitTercBean.get(pesqArrayList);
    }

    public List<MovEquipVisitTercBean> movEquipVisitTercAbertoId(Long idMovEquipVisitTerc){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqMovId(idMovEquipVisitTerc));
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        return movEquipVisitTercBean.get(pesqArrayList);
    }

    public List<MovEquipVisitTercBean> movEquipVisitTercEntradaList(ArrayList<Long> idMovEquipVisitTercArrayList){
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        return movEquipVisitTercBean.in("idMovEquipVisitTerc", idMovEquipVisitTercArrayList);
    }

    public List<MovEquipVisitTercBean> movEquipVisitTercAllList(){
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        return movEquipVisitTercBean.orderBy("idMovEquipVisitTerc", false);
    }

    public List<MovEquipVisitTercBean> movEquipVisitTercEntradaList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEntrada());
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        return movEquipVisitTercBean.getDateHour(pesqArrayList, "idMovEquipVisitTerc", false);
    }

    public ArrayList<String> movEquipVisitTercAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("MovEquipVisitTerc");
        MovEquipVisitTercBean movEquipVisitTercBean = new MovEquipVisitTercBean();
        List<MovEquipVisitTercBean> movEquipVisitTercList = movEquipVisitTercBean.orderBy("idMovEquipVisitTerc", true);
        for (MovEquipVisitTercBean movEquipVisitTercBeanBD : movEquipVisitTercList) {
            dadosArrayList.add(dadosEquipVisitTerc(movEquipVisitTercBeanBD));
        }
        movEquipVisitTercList.clear();
        return dadosArrayList;
    }

    public ArrayList<Long> idMovEquipVisitTercArrayList(String objeto) throws Exception {

        ArrayList<Long> idMovEquipVisitTercArrayList = new ArrayList<>();

        JSONObject jObjMovEquipVisitTerc = new JSONObject(objeto);
        JSONArray jsonArrayMovEquipVisitTerc = jObjMovEquipVisitTerc.getJSONArray("movequipvisitterc");

        for (int i = 0; i < jsonArrayMovEquipVisitTerc.length(); i++) {

            JSONObject objBol = jsonArrayMovEquipVisitTerc.getJSONObject(i);
            Gson gsonBol = new Gson();
            MovEquipVisitTercBean movEquipVisitTercBean = gsonBol.fromJson(objBol.toString(), MovEquipVisitTercBean.class);

            idMovEquipVisitTercArrayList.add(movEquipVisitTercBean.getIdMovEquipVisitTerc());

        }

        return idMovEquipVisitTercArrayList;

    }

    private String dadosEquipVisitTerc(MovEquipVisitTercBean movEquipVisitTercBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(movEquipVisitTercBean, movEquipVisitTercBean.getClass()).toString();
    }

    public String dadosEnvioMovEquipVisitTerc() {
        return dadosMovEquipVisitTerc(movEquipVisitTercFechadoList());
    }

    private String dadosMovEquipVisitTerc(List<MovEquipVisitTercBean> movEquipVisitTercList){

        JsonArray jsonArrayBoletim = new JsonArray();

        for (MovEquipVisitTercBean movEquipVisitTercBean : movEquipVisitTercList) {
            Gson gsonMovEquipVisitTerc = new Gson();
            jsonArrayBoletim.add(gsonMovEquipVisitTerc.toJsonTree(movEquipVisitTercBean, movEquipVisitTercBean.getClass()));
        }

        movEquipVisitTercList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("movequipvisitterc", jsonArrayBoletim);

        return jsonBoletim.toString();
    }

    private EspecificaPesquisa getPesqMovId(Long idMovEquipVisitTerc){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idMovEquipVisitTerc");
        pesquisa.setValor(idMovEquipVisitTerc);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMovAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusMovEquipVisitTerc");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMovFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusMovEquipVisitTerc");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqMovEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusMovEquipVisitTerc");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusEntrada(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusEntradaSaidaMovEquipVisitTerc");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqDtrhLongDia(Long dtrhLongDia){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dthrLongMovEquipVisitTerc");
        pesquisa.setValor(dtrhLongDia);
        pesquisa.setTipo(2);
        return pesquisa;
    }

}
