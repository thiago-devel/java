//using LCR.Comum.Dominio.Entidades;
//using LCR.Comum.Dominio.Entidades.Base;
//using LCR.Comum.IoC;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
//using System.Web.Mvc;
using System.Collections;
//using LCR.Crediario.Dominio.Entidades;
//using LCR.Crediario.Dominio.Interfaces.Negocio;
using System.Data;
using System.IO;
//using iTextSharp.text;
//using iTextSharp.text.pdf;
//using LCR.Crediario.Negocio;
//using LCR.Crediario.Dominio.br.com.cardif.uatparceiros;

namespace LCR.Web.Controllers
{
    /*[Authorize]
    public class Cred_SeguroSupProController : BaseController
    {
        private ISeguroSupProBO BO { get; set; }
        //private CustomerSalesService client = new CustomerSalesService();
        //CustomerSalesServicesClient client = new CustomerSalesServicesClient();
        

        public Cred_SeguroSupProController(ISeguroSupProBO adminBO)
        {
            this.BO = adminBO;

            ViewBag.Controller = "SeguroSupPro";
        }

        [Authorize]
        public ActionResult Index()
        {
            return base.ValidarPermissao(View());
        }

        public ActionResult salvarIncluirSeguro(string TipoServico,string NomeCompleto,string CPF,string Sexo,string DataNascimento,string EstadoCivil,string Cep,string Cidade,
                                                string UF,string TipoLogradouro,string Logradouro,string Numero,string Bairro,string Complemento,string Telefone,
                                                string Celular,string Email1, string TipoCartao,string NomeCartao,string CodigoSeguranca,string NumeroCartao,string Validade)
        {
            string msg = "";

            LCR.Crediario.Dominio.Entidades.SeguroSupPro segurosuppro = new LCR.Crediario.Dominio.Entidades.SeguroSupPro();

            segurosuppro.TipoServico = !string.IsNullOrEmpty(TipoServico) ? int.Parse(TipoServico) : 0;
            segurosuppro.NomeCompleto = NomeCompleto;
            segurosuppro.CPF = Util.RetirarCaracteresCpf(CPF);
            segurosuppro.Sexo = Sexo;
            segurosuppro.DataNascimento = Convert.ToDateTime(DataNascimento);//
            segurosuppro.EstadoCivil = EstadoCivil;
            segurosuppro.Cep = Cep;//
            segurosuppro.Cidade = Cidade;//
            segurosuppro.UF = UF;//
            segurosuppro.TipoLogradouro = TipoLogradouro;
            segurosuppro.Logradouro = Logradouro;//
            segurosuppro.Numero = Convert.ToInt32(Numero);//
            segurosuppro.Bairro = Bairro;//
            segurosuppro.Complemento = Complemento;//
            segurosuppro.Telefone = Util.RetirarCaracteresTelefone(Telefone);
            segurosuppro.Celular = Util.RetirarCaracteresTelefone(Celular);
            segurosuppro.Email1 = Email1;
            segurosuppro.TipoCartao = TipoCartao;
            segurosuppro.NomeCartao = NomeCartao;
            segurosuppro.CodigoSeguranca = CodigoSeguranca;
            segurosuppro.NumeroCartao = NumeroCartao;
            segurosuppro.Validade = Convert.ToDateTime("1985-01-26");
            segurosuppro.Usuario.Login = base.Usuario.Login;

            if (Util.ValidaCPF(CPF))
            {
                if (this.BO.salvarIncluirSeguro(segurosuppro, out msg))
                {
                    CustomerSalesServicesClient client = new CustomerSalesServicesClient();

                    customer cus = new customer();
                    cus.birthDate = segurosuppro.DataNascimento;


                    customer c = new customer();
                    addressDTO add = new addressDTO();
                    add.addressDetail = segurosuppro.Logradouro;
                    add.type = addressType.RESIDENTIAL;
                    add.addressNumber = segurosuppro.Numero;
                    add.addressPostalCode = segurosuppro.Cep;
                    add.city = segurosuppro.Cidade;
                    add.neighborhood = segurosuppro.Bairro;
                    add.state = segurosuppro.UF;

                    phoneDTO phone = new phoneDTO();
                    phone.phoneNumber = segurosuppro.Telefone;
                    phone.type = phoneType.RESIDENTIAL;

                    c.fullName = segurosuppro.NomeCompleto;
                    c.gender = gender.MALE;

                    identityDTO iden = new identityDTO();
                    iden.documentType = document.CPF;
                    iden.documentValue = segurosuppro.CPF;


                    c.maritalStatus = maritalStatus.SINGLE;
                    //criar novo contato para celular e repetir 
                    //cus.contacts[1].phone.phoneNumber = segurosuppro.Celular;
                    //cus.contacts[1].phone.type = phoneType.MOBILE;

                    cardPaymentDTO card = new cardPaymentDTO();
                    card.cardDisplayName = segurosuppro.NomeCartao;
                    card.cardFlag = "luiza";//segurosuppro.TipoCartao;
                    card.cardNumber = segurosuppro.NumeroCartao;
                    card.cardSecurityCode = segurosuppro.CodigoSeguranca;
                    card.cardValidity = Convert.ToDateTime("1985-01-26"); //segurosuppro.Validade;
                    card.cardValue = Convert.ToDecimal("19,90");

                    productDTO prod = new productDTO();
                    prod.descripton = "CARTÃO PROTEGIDO";
                    prod.ID = 25;
                    
                    
                    contactDTO cc = new contactDTO();
                    cc.address = add;
                    cc.phone = phone;
                    
                    
                    contactDTO[] contatos = new contactDTO[] { cc };
                    c.contacts = contatos;
                    c.identity = iden;

                    paymentMethod payM = new paymentMethod();
                    payM.cardPayment = card;


                    identityDTO idensales = new identityDTO();
                    idensales.documentType = document.CPF;
                    idensales.documentValue = "07558609690";


                    loginDTO log = new loginDTO();
                    log.username = "075586";
                    log.password = "075586";

                    partner part = new partner();
                    part.ID = 001;

                    salesman salman = new salesman();
                    salman.identity = idensales;
                    salman.login = log;
                    salman.partner = part;
                    salman.operatorName = "Saulo Mezencio";

                    sale sa = new sale();
                    sa.customer = c;
                    sa.paymentMethod = payM;
                    sa.product = prod;
                    sa.salesman = salman;

                    doSaleRequest request = new doSaleRequest();
                    request.sale = sa;


                    doSaleResponse response = null;

                    response = client.doSale(request);

                 }
            }
            else
            {
                var resultado = new
                {
                    Sucesso = false,
                    Mensagem = "CPF Inválido"
                };
                return Json(resultado, JsonRequestBehavior.AllowGet);
            }
            return Json(new { Sucesso = false, Mensagem = msg.Replace("\r\n", "<br/>") }, JsonRequestBehavior.AllowGet);
        }

        public JsonResult GerarCertificado()
        {
            CustomerSalesServicesClient client = new CustomerSalesServicesClient();
            //client = new CustomerSalesService();

            doSaleRequest requestdoSale = new doSaleRequest();
            doSaleResponse responsedoSale = null;
            responsedoSale = client.doSale(requestdoSale);


            retrieveSalesRequest requestretrieveSales = new retrieveSalesRequest();
            sale[] responseretrieveSales = null;
            responseretrieveSales = client.retrieveSales(requestretrieveSales);


            #region certificado
            string sourceCS = @"D:\Cardif_suppro\BR_PR_CS_Magazine Luiza_BP_Superprotegido_v1.0_17062015a.jpg";
            string outputCS = @"D:\Cardif_suppro\teste2.pdf";

            System.IO.MemoryStream msCS = new System.IO.MemoryStream();

            iTextSharp.text.Image imageCS = iTextSharp.text.Image.GetInstance(sourceCS);
            using (FileStream fsCS = new FileStream(outputCS, FileMode.Create, FileAccess.Write, FileShare.None))
            {
                using (Document docCS = new Document(imageCS))
                {
                    using (PdfWriter writerCS = PdfWriter.GetInstance(docCS, msCS))
                    {
                        docCS.Open();
                        imageCS.SetAbsolutePosition(0, 0);
                        //writer.DirectContent.AddImage(image); //fica do texto.
                        docCS.Add(imageCS);

                        PdfContentByte cb = writerCS.DirectContent;
                        ColumnText nome = new ColumnText(cb);
                        //alinhamento(posição horizontal, altura da cx, largura da cx, posição vertical) 
                        nome.SetSimpleColumn(130, 90, 680, 1470);
                        nome.AddText(new Chunk(responseretrieveSales[0].customer.fullName.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        nome.Go();

                        ColumnText cpf = new ColumnText(cb);
                        cpf.SetSimpleColumn(725, 90, 900, 1470);
                        cpf.AddText(new Chunk(responseretrieveSales[0].customer.identity.documentValue.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        cpf.Go();

                        //Rectangle rect1 = new Rectangle(1033, 130, 1151, 1470);
                        ColumnText dtNasc = new ColumnText(cb);
                        dtNasc.SetSimpleColumn(1033, 130, 1151, 1470);
                        dtNasc.AddText(new Chunk(responseretrieveSales[0].customer.birthDate.ToShortDateString(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtNasc.Go();

                        ColumnText endereco = new ColumnText(cb);
                        endereco.SetSimpleColumn(147, 90, 619, 1437);
                        endereco.AddText(new Chunk(responseretrieveSales[0].customer.contacts[0].address.addressDetail.ToUpper() + ", "
                            + responseretrieveSales[0].customer.contacts[0].address.addressNumber + " - "
                            + responseretrieveSales[0].customer.contacts[0].address.neighborhood.ToUpper() + ""
                            , FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        endereco.Go();

                        ColumnText cep = new ColumnText(cb);
                        cep.SetSimpleColumn(660, 90, 817, 1433);
                        cep.AddText(new Chunk(Util.FormatarCEP(responseretrieveSales[0].customer.contacts[0].address.addressPostalCode.ToUpper()), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        cep.Go();

                        ColumnText cidade = new ColumnText(cb);
                        cidade.SetSimpleColumn(870, 90, 1060, 1437);
                        cidade.AddText(new Chunk(responseretrieveSales[0].customer.contacts[0].address.city.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        cidade.Go();

                        ColumnText uf = new ColumnText(cb);
                        uf.SetSimpleColumn(1090, 90, 1155, 1437);
                        uf.AddText(new Chunk(responseretrieveSales[0].customer.contacts[0].address.state.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        uf.Go();

                        ColumnText numproposta = new ColumnText(cb);
                        numproposta.SetSimpleColumn(150, 90, 330, 1370);
                        numproposta.AddText(new Chunk(responsedoSale.certificate.contractNumber.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numproposta.Go();

                        ColumnText numcertificado = new ColumnText(cb);
                        numcertificado.SetSimpleColumn(415, 90, 615, 1370);
                        numcertificado.AddText(new Chunk(responsedoSale.certificate.contractNumber.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numcertificado.Go();


                        ColumnText dtemissao = new ColumnText(cb);
                        dtemissao.SetSimpleColumn(700, 90, 800, 1370);
                        dtemissao.AddText(new Chunk(responsedoSale.certificate.certificateCreationDate.ToShortDateString(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtemissao.Go();


                        ColumnText dtinivigencia = new ColumnText(cb);
                        dtinivigencia.SetSimpleColumn(940, 90, 1023, 1370);
                        dtinivigencia.AddText(new Chunk(responsedoSale.certificate.certificateValidityInitDate.ToShortDateString(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtinivigencia.Go();


                        ColumnText dtfimvigencia = new ColumnText(cb);
                        dtfimvigencia.SetSimpleColumn(1046, 90, 1146, 1370);
                        dtfimvigencia.AddText(new Chunk(responsedoSale.certificate.certificateValidityEndDate.ToShortDateString(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtfimvigencia.Go();


                        ColumnText premiomensaltotal = new ColumnText(cb);
                        premiomensaltotal.SetSimpleColumn(224, 90, 370, 1330);
                        premiomensaltotal.AddText(new Chunk("premio mensal", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        premiomensaltotal.Go();


                        ColumnText IOF = new ColumnText(cb);
                        IOF.SetSimpleColumn(437, 90, 610, 1330);
                        IOF.AddText(new Chunk(responseretrieveSales[0].paymentMethod.cardPayment.cardDisplayName, FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        IOF.Go();


                        ColumnText numcartao = new ColumnText(cb);
                        numcartao.SetSimpleColumn(683, 90, 880, 1330);
                        numcartao.AddText(new Chunk(responseretrieveSales[0].paymentMethod.cardPayment.cardNumber, FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numcartao.Go();


                        ColumnText numsorte = new ColumnText(cb);
                        numsorte.SetSimpleColumn(950, 90, 1146, 1330);
                        numsorte.AddText(new Chunk("numero sorte", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numsorte.Go();


                        ColumnText cobersaque = new ColumnText(cb);
                        cobersaque.SetSimpleColumn(390, 90, 490, 1027);
                        cobersaque.AddText(new Chunk("1", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobersaque.Go();

                        ColumnText cobersaquelimitmax = new ColumnText(cb);
                        cobersaquelimitmax.SetSimpleColumn(783, 90, 885, 1020);
                        cobersaquelimitmax.AddText(new Chunk("1-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobersaquelimitmax.Go();

                        ColumnText coberrouboqualificado = new ColumnText(cb);
                        numsorte.SetSimpleColumn(390, 90, 490, 983);
                        numsorte.AddText(new Chunk("2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        numsorte.Go();

                        ColumnText coberrouboqualificadolimitmax = new ColumnText(cb);
                        coberrouboqualificadolimitmax.SetSimpleColumn(841, 90, 946, 994);
                        coberrouboqualificadolimitmax.AddText(new Chunk("2-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberrouboqualificadolimitmax.Go();

                        ColumnText coberroubocx = new ColumnText(cb);
                        numsorte.SetSimpleColumn(390, 90, 490, 937);
                        numsorte.AddText(new Chunk("3", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        numsorte.Go();

                        ColumnText coberroubocxlimitmax = new ColumnText(cb);
                        coberroubocxlimitmax.SetSimpleColumn(783, 90, 893, 929);
                        coberroubocxlimitmax.AddText(new Chunk("3-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberroubocxlimitmax.Go();

                        ColumnText cobermorte = new ColumnText(cb);
                        cobermorte.SetSimpleColumn(390, 90, 490, 900);
                        cobermorte.AddText(new Chunk("4", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermorte.Go();

                        ColumnText cobermortelimitmax = new ColumnText(cb);
                        cobermortelimitmax.SetSimpleColumn(1033, 90, 1140, 873);
                        cobermortelimitmax.AddText(new Chunk("4,5-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermortelimitmax.Go();

                        ColumnText coberinvaliacidente = new ColumnText(cb);
                        coberinvaliacidente.SetSimpleColumn(390, 90, 490, 871);
                        coberinvaliacidente.AddText(new Chunk("5", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberinvaliacidente.Go();

                        ColumnText coberdesemprego = new ColumnText(cb);
                        coberdesemprego.SetSimpleColumn(390, 90, 490, 835);
                        coberdesemprego.AddText(new Chunk("6", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdesemprego.Go();

                        ColumnText coberdesempregolimitmax = new ColumnText(cb);
                        coberdesempregolimitmax.SetSimpleColumn(1033, 90, 1140, 827);
                        coberdesempregolimitmax.AddText(new Chunk("6-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdesempregolimitmax.Go();

                        ColumnText cobermortecrime = new ColumnText(cb);
                        cobermortecrime.SetSimpleColumn(390, 90, 490, 794);
                        cobermortecrime.AddText(new Chunk("7", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermortecrime.Go();

                        ColumnText cobermortecrimelimitmax = new ColumnText(cb);
                        cobermortecrimelimitmax.SetSimpleColumn(715, 90, 1000, 794);
                        cobermortecrimelimitmax.AddText(new Chunk("7-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermortecrimelimitmax.Go();

                        ColumnText coberinvalicrime = new ColumnText(cb);
                        coberinvalicrime.SetSimpleColumn(390, 90, 490, 754);
                        coberinvalicrime.AddText(new Chunk("8", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberinvalicrime.Go();

                        ColumnText coberinvalicrimelimitmax = new ColumnText(cb);
                        coberinvalicrimelimitmax.SetSimpleColumn(715, 90, 1000, 754);
                        coberinvalicrimelimitmax.AddText(new Chunk("8-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberinvalicrimelimitmax.Go();

                        ColumnText coberdiahospitalcrime = new ColumnText(cb);
                        coberdiahospitalcrime.SetSimpleColumn(390, 90, 490, 709);
                        coberdiahospitalcrime.AddText(new Chunk("9", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdiahospitalcrime.Go();

                        ColumnText coberdiahospitalcrimelimitmax = new ColumnText(cb);
                        coberdiahospitalcrimelimitmax.SetSimpleColumn(715, 90, 1000, 709);
                        coberdiahospitalcrimelimitmax.AddText(new Chunk("9-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdiahospitalcrimelimitmax.Go();


                        ColumnText valorsorteio = new ColumnText(cb);
                        valorsorteio.SetSimpleColumn(720, 90, 799, 582);
                        valorsorteio.AddText(new Chunk("10.000,00", FontFactory.GetFont("Arial", 14, iTextSharp.text.Font.NORMAL)));
                        valorsorteio.Go();


                        ColumnText prolaboreporc = new ColumnText(cb);
                        prolaboreporc.SetSimpleColumn(282, 90, 318, 235);
                        prolaboreporc.AddText(new Chunk("50,00", FontFactory.GetFont("Arial", 14, iTextSharp.text.Font.NORMAL)));
                        prolaboreporc.Go();


                        ColumnText prolaborevalor = new ColumnText(cb);
                        prolaborevalor.SetSimpleColumn(363, 90, 406, 235);
                        prolaborevalor.AddText(new Chunk("5,87", FontFactory.GetFont("Arial", 14, iTextSharp.text.Font.NORMAL)));
                        prolaborevalor.Go();


                        //doc.Add(new Paragraph("My first PDF"));
                        //doc.Add(new iTextSharp.text.Chunk("hello world", FontFactory.GetFont("Arial", 14, iTextSharp.text.Font.BOLD)));
                        docCS.Close();

                        byte[] pdfByte = msCS.ToArray();
                        string pdfbase64String = Convert.ToBase64String(pdfByte);

                        return Json(new
                        {
                            pdfbase64String
                        }, JsonRequestBehavior.AllowGet);
                    }
                }
            }
            #endregion
        }

        public JsonResult GerarPropostaAdesao()
        {
            CustomerSalesServicesClient client = new CustomerSalesServicesClient();
            doSaleRequest request = new doSaleRequest();
            doSaleResponse response = null;

            response = client.doSale(request);

            #region propostaadesao
            string sourcePA = @"D:\Cardif_suppro\BR_PR_PA_Magazine Luiza_BP_Superprotegido_v1.0_17062015a.jpg";
            string outputPA = @"D:\Cardif_suppro\testePA.pdf";

            System.IO.MemoryStream msPA = new System.IO.MemoryStream();

            iTextSharp.text.Image imagePA = iTextSharp.text.Image.GetInstance(sourcePA);
            using (FileStream fsPA = new FileStream(outputPA, FileMode.Create, FileAccess.Write, FileShare.None))
            {
                using (Document docPA = new Document(imagePA))
                {
                    using (PdfWriter writerPA = PdfWriter.GetInstance(docPA, msPA))
                    {
                        docPA.Open();
                        imagePA.SetAbsolutePosition(0, 0);
                        //writer.DirectContent.AddImage(image); //fica do texto.
                        docPA.Add(imagePA);

                        PdfContentByte cb = writerPA.DirectContent;
                        ColumnText nome = new ColumnText(cb);
                        //alinhamento(posição horizontal, altura da cx, largura da cx, posição vertical) 
                        nome.SetSimpleColumn(130, 90, 680, 1535);
                        nome.AddText(new Chunk(response.certificate.customer.fullName.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        nome.Go();

                        ColumnText cpf = new ColumnText(cb);
                        cpf.SetSimpleColumn(725, 90, 900, 1535);
                        cpf.AddText(new Chunk(response.certificate.customer.identity.documentValue.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        cpf.Go();

                        //Rectangle rect1 = new Rectangle(1033, 130, 1151, 1470);
                        ColumnText dtNasc = new ColumnText(cb);
                        dtNasc.SetSimpleColumn(1033, 130, 1151, 1535);
                        dtNasc.AddText(new Chunk(response.certificate.customer.birthDate.ToShortDateString(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtNasc.Go();

                        ColumnText endereco = new ColumnText(cb);
                        endereco.SetSimpleColumn(147, 90, 619, 1502);
                        endereco.AddText(new Chunk(response.certificate.customer.contacts[0].address.addressDetail.ToUpper() + ", "
                            + response.certificate.customer.contacts[0].address.addressNumber + " - "
                            + response.certificate.customer.contacts[0].address.neighborhood.ToUpper() + ""
                            , FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        endereco.Go();

                        ColumnText cep = new ColumnText(cb);
                        cep.SetSimpleColumn(660, 90, 817, 1498);
                        cep.AddText(new Chunk(Util.FormatarCEP(response.certificate.customer.contacts[0].address.addressPostalCode.ToUpper()), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        cep.Go();

                        ColumnText cidade = new ColumnText(cb);
                        cidade.SetSimpleColumn(870, 90, 1060, 1502);
                        cidade.AddText(new Chunk(response.certificate.customer.contacts[0].address.city.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        cidade.Go();

                        ColumnText uf = new ColumnText(cb);
                        uf.SetSimpleColumn(1090, 90, 1155, 1502);
                        uf.AddText(new Chunk(response.certificate.customer.contacts[0].address.state.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        uf.Go();

                        ColumnText numproposta = new ColumnText(cb);
                        numproposta.SetSimpleColumn(163, 90, 374, 1435);
                        numproposta.AddText(new Chunk(response.certificate.contractNumber.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numproposta.Go();


                        ColumnText numcartao1 = new ColumnText(cb);
                        numcartao1.SetSimpleColumn(436, 90, 696, 1435);
                        numcartao1.AddText(new Chunk(response.certificate.contractNumber.ToUpper(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numcartao1.Go();


                        ColumnText dtinivigencia = new ColumnText(cb);
                        dtinivigencia.SetSimpleColumn(830, 90, 987, 1435);
                        dtinivigencia.AddText(new Chunk(response.certificate.certificateValidityInitDate.ToShortDateString(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtinivigencia.Go();


                        ColumnText dtfimvigencia = new ColumnText(cb);
                        dtfimvigencia.SetSimpleColumn(1010, 90, 1150, 1435);
                        dtfimvigencia.AddText(new Chunk(response.certificate.certificateValidityEndDate.ToShortDateString(), FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtfimvigencia.Go();


                        ColumnText premiomensaltotal = new ColumnText(cb);
                        premiomensaltotal.SetSimpleColumn(224, 90, 437, 1392);
                        premiomensaltotal.AddText(new Chunk("Premio Mensal", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        premiomensaltotal.Go();


                        ColumnText IOF = new ColumnText(cb);
                        IOF.SetSimpleColumn(491, 90, 795, 1392);
                        IOF.AddText(new Chunk("IOF", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        IOF.Go();


                        ColumnText numsorte = new ColumnText(cb);
                        numsorte.SetSimpleColumn(855, 90, 1150, 1392);
                        numsorte.AddText(new Chunk("numero sorte", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numsorte.Go();



                        ColumnText formapag = new ColumnText(cb);
                        formapag.SetSimpleColumn(194, 90, 450, 1335);
                        formapag.AddText(new Chunk("Cartao de Credito TEF - ", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        formapag.Go();


                        ColumnText banconum = new ColumnText(cb);
                        banconum.SetSimpleColumn(514, 90, 692, 1330);
                        banconum.AddText(new Chunk("banco", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        banconum.Go();


                        ColumnText agencianum = new ColumnText(cb);
                        agencianum.SetSimpleColumn(766, 90, 913, 1330);
                        agencianum.AddText(new Chunk("agencia", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        agencianum.Go();


                        ColumnText contacorrente = new ColumnText(cb);
                        contacorrente.SetSimpleColumn(1010, 90, 1157, 1330);
                        contacorrente.AddText(new Chunk("C Corrente", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        contacorrente.Go();


                        ColumnText nometitcartao = new ColumnText(cb);
                        nometitcartao.SetSimpleColumn(225, 90, 518, 1310);
                        nometitcartao.AddText(new Chunk("Saulo Mezêncio da Silva", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        nometitcartao.Go();


                        ColumnText numcartao2 = new ColumnText(cb);
                        numcartao2.SetSimpleColumn(594, 90, 912, 1303);
                        numcartao2.AddText(new Chunk("numero cartao 2 ", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        numcartao2.Go();


                        ColumnText dtvalidade = new ColumnText(cb);
                        dtvalidade.SetSimpleColumn(1010, 90, 1157, 1303);
                        dtvalidade.AddText(new Chunk("08/17", FontFactory.GetFont("Arial", 16, iTextSharp.text.Font.NORMAL)));
                        dtvalidade.Go();



                        ColumnText cobersaque = new ColumnText(cb);
                        cobersaque.SetSimpleColumn(390, 90, 490, 1062);
                        cobersaque.AddText(new Chunk("1", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobersaque.Go();

                        ColumnText cobersaquelimitmax = new ColumnText(cb);
                        cobersaquelimitmax.SetSimpleColumn(783, 90, 885, 1055);
                        cobersaquelimitmax.AddText(new Chunk("1-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobersaquelimitmax.Go();

                        ColumnText coberrouboqualificado = new ColumnText(cb);
                        coberrouboqualificado.SetSimpleColumn(390, 90, 490, 1018);
                        coberrouboqualificado.AddText(new Chunk("2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberrouboqualificado.Go();

                        ColumnText coberrouboqualificadolimitmax = new ColumnText(cb);
                        coberrouboqualificadolimitmax.SetSimpleColumn(841, 90, 946, 1029);
                        coberrouboqualificadolimitmax.AddText(new Chunk("2-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberrouboqualificadolimitmax.Go();

                        ColumnText coberroubocx = new ColumnText(cb);
                        coberroubocx.SetSimpleColumn(390, 90, 490, 982);
                        coberroubocx.AddText(new Chunk("3", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberroubocx.Go();

                        ColumnText coberroubocxlimitmax = new ColumnText(cb);
                        coberroubocxlimitmax.SetSimpleColumn(783, 90, 893, 964);
                        coberroubocxlimitmax.AddText(new Chunk("3-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberroubocxlimitmax.Go();

                        ColumnText cobermorte = new ColumnText(cb);
                        cobermorte.SetSimpleColumn(390, 90, 490, 935);
                        cobermorte.AddText(new Chunk("4", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermorte.Go();

                        ColumnText cobermortelimitmax = new ColumnText(cb);
                        cobermortelimitmax.SetSimpleColumn(1033, 90, 1140, 908);
                        cobermortelimitmax.AddText(new Chunk("4,5-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermortelimitmax.Go();

                        ColumnText coberinvaliacidente = new ColumnText(cb);
                        coberinvaliacidente.SetSimpleColumn(390, 90, 490, 906);
                        coberinvaliacidente.AddText(new Chunk("5", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberinvaliacidente.Go();

                        ColumnText coberdesemprego = new ColumnText(cb);
                        coberdesemprego.SetSimpleColumn(390, 90, 490, 870);
                        coberdesemprego.AddText(new Chunk("6", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdesemprego.Go();

                        ColumnText coberdesempregolimitmax = new ColumnText(cb);
                        coberdesempregolimitmax.SetSimpleColumn(1033, 90, 1140, 862);
                        coberdesempregolimitmax.AddText(new Chunk("6-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdesempregolimitmax.Go();

                        ColumnText cobermortecrime = new ColumnText(cb);
                        cobermortecrime.SetSimpleColumn(390, 90, 490, 829);
                        cobermortecrime.AddText(new Chunk("7", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermortecrime.Go();

                        ColumnText cobermortecrimelimitmax = new ColumnText(cb);
                        cobermortecrimelimitmax.SetSimpleColumn(715, 90, 1000, 829);
                        cobermortecrimelimitmax.AddText(new Chunk("7-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        cobermortecrimelimitmax.Go();

                        ColumnText coberinvalicrime = new ColumnText(cb);
                        coberinvalicrime.SetSimpleColumn(390, 90, 490, 789);
                        coberinvalicrime.AddText(new Chunk("8", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberinvalicrime.Go();

                        ColumnText coberinvalicrimelimitmax = new ColumnText(cb);
                        coberinvalicrimelimitmax.SetSimpleColumn(715, 90, 1000, 789);
                        coberinvalicrimelimitmax.AddText(new Chunk("8-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberinvalicrimelimitmax.Go();

                        ColumnText coberdiahospitalcrime = new ColumnText(cb);
                        coberdiahospitalcrime.SetSimpleColumn(390, 90, 490, 744);
                        coberdiahospitalcrime.AddText(new Chunk("9", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdiahospitalcrime.Go();

                        ColumnText coberdiahospitalcrimelimitmax = new ColumnText(cb);
                        coberdiahospitalcrimelimitmax.SetSimpleColumn(715, 90, 1000, 744);
                        coberdiahospitalcrimelimitmax.AddText(new Chunk("9-2", FontFactory.GetFont("Arial", 18, iTextSharp.text.Font.NORMAL)));
                        coberdiahospitalcrimelimitmax.Go();


                        ColumnText valorsorteio = new ColumnText(cb);
                        valorsorteio.SetSimpleColumn(171, 90, 254, 535);
                        valorsorteio.AddText(new Chunk("10.000,00", FontFactory.GetFont("Arial", 14, iTextSharp.text.Font.NORMAL)));
                        valorsorteio.Go();


                        ColumnText prolaboreporc = new ColumnText(cb);
                        prolaboreporc.SetSimpleColumn(282, 90, 318, 232);
                        prolaboreporc.AddText(new Chunk("50,00", FontFactory.GetFont("Arial", 14, iTextSharp.text.Font.NORMAL)));
                        prolaboreporc.Go();


                        ColumnText prolaborevalor = new ColumnText(cb);
                        numsorte.SetSimpleColumn(363, 90, 406, 232);
                        numsorte.AddText(new Chunk("5,87", FontFactory.GetFont("Arial", 14, iTextSharp.text.Font.NORMAL)));
                        numsorte.Go();

                        docPA.Close();

                        byte[] pdfByte = msPA.ToArray();
                        string pdfbase64String = Convert.ToBase64String(pdfByte);

                        return Json(new
                        {
                            pdfbase64String
                        }, JsonRequestBehavior.AllowGet);
                    }
                }
            }
            #endregion
        }
    }
    */

}