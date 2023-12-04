package com.origin.admin;

import com.origin.admin.common.tools.core.JwtUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OriginAdminApplicationTests {

    @Resource
    private JwtUtils jwtUtils;

    @Test
    void contextLoads() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3MDQiLCJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTI0MzUxMSwiZXhwIjoxNzAyNjYzNjQyfQ.nQMQ3GssOaBWYvmQ-ef8ZASAXHPjViGZrmaFbzJC1w0";
        String username = jwtUtils.extractUsername(token);
        System.out.println(username);
    }

    public int maxProfit(int max_k, int[] prices) {
        int N = prices.length;
        int[][][]dp = new int[N][max_k + 1][2];
        for(int i = 0; i < N;i++){
            for(int j = max_k; j >= 1;j--){
                if(i == 0){
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0],
                        dp[i - 1][j][1] + prices[i]);

                dp[i][j][1] = Math.max(dp[i - 1][j][1],
                        dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[N - 1][max_k][0];
    }

}
