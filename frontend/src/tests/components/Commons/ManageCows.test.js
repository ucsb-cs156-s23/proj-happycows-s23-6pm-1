import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import ManageCows from "main/components/Commons/ManageCows"; 
import userCommonsFixtures from "fixtures/userCommonsFixtures";

describe("ManageCows tests", () => {
    test("renders without crashing", () => {
        render(
            <ManageCows userCommons = {userCommonsFixtures.oneUserCommons[0]} onBuy={(userCommons) => { console.log("onBuy called:",userCommons); }} onSell={ (userCommons) => { console.log("onSell called:",userCommons); }} />
        );
    });

    test("buying and selling a cow", async () => {
        const mockBuy = jest.fn();
        const mockSell = jest.fn();

        render(
            <ManageCows userCommons = {userCommonsFixtures.oneUserCommons[0]} onBuy={mockBuy} onSell={mockSell} />
        );

        const buyButton = screen.getByTestId("buy-cow-button");
        const sellButton = screen.getByTestId("sell-cow-button");
        
        fireEvent.click(buyButton);
        await waitFor( ()=>expect(mockBuy).toHaveBeenCalledWith(userCommonsFixtures.oneUserCommons[0]) );

        fireEvent.click(sellButton);
        await waitFor( ()=>expect(mockSell).toHaveBeenCalledWith(userCommonsFixtures.oneUserCommons[0]) );
        
    });

});