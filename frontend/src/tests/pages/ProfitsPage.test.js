import { render, screen, fireEvent } from "@testing-library/react";
import profitsFixtures from "fixtures/profitsFixtures";
import { apiCurrentUserFixtures } from "fixtures/currentUserFixtures";
import { systemInfoFixtures } from "fixtures/systemInfoFixtures";
import commonsFixtures from "fixtures/commonsFixtures";

import ProfitsPage from "main/pages/ProfitsPage";
import { QueryClient, QueryClientProvider } from "react-query";
import { MemoryRouter } from "react-router-dom";
import axios from "axios";
import AxiosMockAdapter from "axios-mock-adapter";

const mockNavigate = jest.fn();
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useParams: () => ({
        commonsId: 1
    }),
    useNavigate: () => mockNavigate
}));

describe("ProfitsPage tests", () => {
    const queryClient = new QueryClient();
    const axiosMock = new AxiosMockAdapter(axios);

    test("renders ProfitsPage with user info", () => {
        axiosMock.onGet("/api/currentUser").reply(200, apiCurrentUserFixtures.userOnly);
        axiosMock.onGet("/api/systemInfo").reply(200, systemInfoFixtures.showingNeither);
        axiosMock.onGet("/api/profits/all/commonsid").reply(200, []);
        render(
            <QueryClientProvider client={queryClient}>
                <MemoryRouter>
                    <ProfitsPage profits={profitsFixtures.threeProfits} />
                </MemoryRouter>
            </QueryClientProvider>
        );

        const expectedHeaders = ["Amount", "Date", "CowHealth", "NumCows"];

        expectedHeaders.forEach((headerText) => {
            const header = screen.getByText(headerText);
            expect(header).toBeInTheDocument();
        });

        var div = screen.getByTestId("profitspage-div");
        expect(div).toHaveAttribute("style", expect.stringContaining("background-size: cover; background-image: url(PlayPageBackground.png);"));
    });

    test("back button goes back", async () => {
        apiCurrentUserFixtures.userOnly.user.commons = commonsFixtures.oneCommons;
        axiosMock.onGet("/api/currentUser").reply(200, apiCurrentUserFixtures.userOnly);
        axiosMock.onGet("/api/commons/all").reply(200, commonsFixtures.threeCommons);

        render(
            <QueryClientProvider client={queryClient}>
                <MemoryRouter>
                    <ProfitsPage profits={profitsFixtures.threeProfits} commonsId={1} />
                </MemoryRouter>
            </QueryClientProvider>
        );

        expect(screen.getByText("Go Back")).toBeInTheDocument();
        const backButton = screen.getByText("Go Back");
        fireEvent.click(backButton);
        expect(mockNavigate).toHaveBeenCalledWith("/play/1");
    });
});