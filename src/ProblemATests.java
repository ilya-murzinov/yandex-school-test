/**
 * @author Murzinov Ilya, murz42@gmail.com
 *         Date 26.05.14
 */
public class ProblemATests {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    public static void test1() {
        ProblemA.inName = "input_find_find.txt";
        String result = ProblemA.transform();
        System.out.println(result.equals(
                "13\n" +
                        ". 0\n" +
                        "./download_client.sh 1\n" +
                        "./random1000_queries_ukraine.txt 2\n" +
                        "./times.txt 3\n" +
                        "./yandex 4\n" +
                        "./yandex/yandex_kz_domains_random1000_2011-07-26.txt 5\n" +
                        "./yandex/yandex_ru_domains_top1000_2011-07-27.txt 6\n" +
                        "./yandex/yandex_by_domains_top1000_2011-07-27.txt 7\n" +
                        "./yandex/kz 8\n" +
                        "./yandex/kz/random1000 9\n" +
                        "./yandex/kz/random1000/yandex_kz_random1000_2011-07-16.xml 10\n" +
                        "./yandex/ru 11\n" +
                        "./yandex/ru/random1000 12\n"
        ));
    }
    public static void test2() {
        ProblemA.inName = "input_find2.txt";
        String result = ProblemA.transform();
        System.out.println(result.equals(
                "13\n" +
                        ". 0\n" +
                        "./download_client.sh 1\n" +
                        "./random1000_queries_ukraine.txt 2\n" +
                        "./times.txt 3\n" +
                        "./yandex 4\n" +
                        "./yandex/yandex_kz_domains_random1000_2011-07-26.txt 5\n" +
                        "./yandex/yandex_ru_domains_top1000_2011-07-27.txt 6\n" +
                        "./yandex/yandex_by_domains_top1000_2011-07-27.txt 7\n" +
                        "./yandex/kz 8\n" +
                        "./yandex/kz/random1000 9\n" +
                        "./yandex/kz/random1000/yandex_kz_random1000_2011-07-16.xml 10\n" +
                        "./yandex/ru 11\n" +
                        "./yandex/ru/random1000 12\n"
        ));
    }
    public static void test3() {
        ProblemA.inName = "input_find3.txt";
        String result = ProblemA.transform();
        System.out.println(result.equals(
                "11\n" +
                "xcfopucvrmng 0\n" +
                "xcfopucvrmng/dltecdvp 302605823\n" +
                "xcfopucvrmng/dltecdvp/vnqmzzcss 383129889\n" +
                "xcfopucvrmng/dltecdvp/vnqmzzcss/xfmfvgwe 573176923\n" +
                "xcfopucvrmng/dltecdvp/vnqmzzcss/xfmfvgwe/whhtrlhen 674811222\n" +
                "xcfopucvrmng/dltecdvp/btocpcgfaq 703593991\n" +
                "xcfopucvrmng/dltecdvp/btocpcgfaq/iayzvfpksks 993559239\n" +
                "xcfopucvrmng/dltecdvp/btocpcgfaq/iayzvfpksks/sogdldbc 1361982678\n" +
                "xcfopucvrmng/dltecdvp/vnqmzzcss/scoqygpymkk 1494552645\n" +
                "xcfopucvrmng/sfuvcevcqk 1596619043\n" +
                "xcfopucvrmng/sfuvcevcqk/cyjcidcd 1850275023\n"
        ));
    }
    public static void test4() {
        ProblemA.inName = "input_python.txt";
        String result = ProblemA.transform();
        System.out.println(result.equals(
                "14\n" +
                "ecsyxnhs 0\n" +
                "ecsyxnhs/vpmnnpkprnu 33729187\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb 83020817\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf 295599815\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg 340374195\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav 430669240\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav/wrrejjwlh 606943964\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav/wrrejjwlh/sfxyvbtb 647931877\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav/wrrejjwlh/sfxyvbtb/oaiwyhtem 1171917149\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav/wrrejjwlh/sfxyvbtb/oaiwyhtem/vjhtuuixva 1314883831\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav/wrrejjwlh/sfxyvbtb/oaiwyhtem/vjhtuuixva/zfowklxyb 1605123980\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav/wrrejjwlh/sfxyvbtb/unoetjpta 1660574895\n" +
                "ecsyxnhs/vpmnnpkprnu/sppsxgjxbb/bpffadovf/vibfoshg/ikrtoppnlav/wrrejjwlh/sfxyvbtb/unoetjpta/dorhaxadw 1893163145\n" +
                "ecsyxnhs/gldjtkluu 2104396012\n"
        ));
    }

    public static void test5() {
        ProblemA.inName = "input_acm1.txt";
        String result = ProblemA.transform();
        System.out.println(result.equals(
                "19\n" +
                "deodefahdnfq 0\n" +
                "deodefahdnfq/sekygtpymml 36233450\n" +
                "deodefahdnfq/sekygtpymml/uigisxszh 99923371\n" +
                "deodefahdnfq/trysryrurxpt 121487292\n" +
                "deodefahdnfq/trysryrurxpt/arvxracz 340075277\n" +
                "deodefahdnfq/trysryrurxpt/arvxracz/fwaysbkpm 356230392\n" +
                "deodefahdnfq/sekygtpymml/uigisxszh/xojgpnvwhc 370919494\n" +
                "deodefahdnfq/vqatfjxtyasp 474166827\n" +
                "deodefahdnfq/trysryrurxpt/ulsogiei 506207631\n" +
                "deodefahdnfq/sekygtpymml/txrodhyxclzv 533457820\n" +
                "deodefahdnfq/nxvfkbgpx 616862627\n" +
                "deodefahdnfq/nxvfkbgpx/vkssldnghss 671267319\n" +
                "deodefahdnfq/nxvfkbgpx/vkssldnghss/eiyndwhy 684223725\n" +
                "deodefahdnfq/cgsnvnrjj 942548637\n" +
                "deodefahdnfq/nxvfkbgpx/vkssldnghss/eiyndwhy/nzxldutrh 987557877\n" +
                "deodefahdnfq/cgsnvnrjj/zbrbjfhid 1457472852\n" +
                "deodefahdnfq/trysryrurxpt/amuqmwczob 2022565110\n" +
                "deodefahdnfq/sekygtpymml/qvgcsiovotlk 2092175717\n" +
                "deodefahdnfq/trysryrurxpt/ulsogiei/nbxdvmrssnmv 2146704377\n"
        ));
    }

    public static void test6() {
        ProblemA.inName = "input_acm2.txt";
        String result = ProblemA.transform();
        System.out.println(result.equals(
                "18\n" +
                "mnyjfhxqe 0\n" +
                "mnyjfhxqe/dllnjuqqi 661497984\n" +
                "mnyjfhxqe/updgjeuq 760191124\n" +
                "mnyjfhxqe/dllnjuqqi/vlnumncud 810345116\n" +
                "mnyjfhxqe/updgjeuq/jbldnbue 835985970\n" +
                "mnyjfhxqe/dllnjuqqi/vlnumncud/lyyuuwddwxqy 888673170\n" +
                "mnyjfhxqe/dllnjuqqi/vlnumncud/lyyuuwddwxqy/yvgwqwpkh 928058165\n" +
                "mnyjfhxqe/updgjeuq/jbldnbue/dtzkefruaic 964026238\n" +
                "mnyjfhxqe/dhsabgkptsm 1148387680\n" +
                "mnyjfhxqe/updgjeuq/jbldnbue/dtzkefruaic/yolflbrt 1424378509\n" +
                "mnyjfhxqe/dhsabgkptsm/gkexzxjhvnsh 1431482789\n" +
                "mnyjfhxqe/jxzhjoyyyv 1616590006\n" +
                "mnyjfhxqe/jxzhjoyyyv/smlipbglil 1661627847\n" +
                "mnyjfhxqe/dllnjuqqi/vlnumncud/lyyuuwddwxqy/yvgwqwpkh/pzpcljkenqk 1792738463\n" +
                "mnyjfhxqe/updgjeuq/jbldnbue/dtzkefruaic/yolflbrt/wvvtofkimpj 1878703413\n" +
                "mnyjfhxqe/dhsabgkptsm/gkexzxjhvnsh/fvcsloeiwey 2065134840\n" +
                "mnyjfhxqe/jxzhjoyyyv/smlipbglil/wyjqqwex 2080099504\n" +
                "mnyjfhxqe/updgjeuq/jbldnbue/dtzkefruaic/yolflbrt/wvvtofkimpj/otlttawcgpdc 2145877549\n"
        ));
    }

    public static void test7() {
        ProblemA.inName = "input_acm3.txt";
        String result = ProblemA.transform();
        System.out.println(result.equals(
                "12\n" +
                "jlesmlgb 0\n" +
                "jlesmlgb/mwehrzqjpinz 431193463\n" +
                "jlesmlgb/mwehrzqjpinz/jkuwnsebb 431235865\n" +
                "jlesmlgb/mwehrzqjpinz/xsblwdifd 435088450\n" +
                "jlesmlgb/mwehrzqjpinz/xsblwdifd/vxoxqhva 551661792\n" +
                "jlesmlgb/mwehrzqjpinz/xsblwdifd/vxoxqhva/jijntvvkxa 611723733\n" +
                "jlesmlgb/mwehrzqjpinz/xsblwdifd/vxoxqhva/jijntvvkxa/jubwyzhe 705530736\n" +
                "jlesmlgb/mwehrzqjpinz/jkuwnsebb/gjngosxeedz 853176805\n" +
                "jlesmlgb/mwehrzqjpinz/ohjmcsbydpnr 957672997\n" +
                "jlesmlgb/mwehrzqjpinz/ohjmcsbydpnr/uinjqmad 1091280165\n" +
                "jlesmlgb/mwehrzqjpinz/ohjmcsbydpnr/wrieguwk 1457554569\n" +
                "jlesmlgb/mwehrzqjpinz/teyxefif 1506359267\n"
        ));
    }
}
