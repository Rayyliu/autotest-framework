package com.autotest.test.bankInfo;

import com.autotest.annotation.AutoTest;
import com.autotest.base.SpringBootTestBase;
import com.xyd.sc.preloan.facade.dto.info.BankInfoResponseDTO;
import com.xyd.sc.preloan.facade.dto.request.BankInfoGetInfoRequestDTO;
import com.xyd.sc.preloan.facade.service.BankInfoService;
import com.xyd.sc.server.common.enums.BankCardSourceType;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class GetInfo {

    @Resource
    BankInfoService bankInfoService;
    @AutoTest(file = "getBankInfoByIdSuccess.csv")
    public void getBankInfoByIdSuccess(){

        BankInfoGetInfoRequestDTO bankInfoGetInfoRequestDTO = new BankInfoGetInfoRequestDTO();
        bankInfoGetInfoRequestDTO.setType(BankCardSourceType.BORROWER);
        bankInfoGetInfoRequestDTO.setBorrowerId(Long.valueOf("435"));
        bankInfoGetInfoRequestDTO.setBorrowerCode("32a63863501c4cf0affe51725f971b65");
//        bankInfoGetInfoRequestDTO.setChannelUserType();
//        bankInfoGetInfoRequestDTO.setCode()
////        bankInfoGetInfoRequestDTO.setEnterpriseCode();
////        bankInfoGetInfoRequestDTO.setMerchantId();;
//        bankInfoGetInfoRequestDTO.setEnterpriseCode();
//        bankInfoGetInfoRequestDTO.setMerchantId();
        BankInfoResponseDTO result = bankInfoService.getInfoById(bankInfoGetInfoRequestDTO);
        System.out.println(result);
    }
}
