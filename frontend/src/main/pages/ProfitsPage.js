import React from "react";
import Profits from "main/components/Commons/Profits";
import Background from "../../assets/PlayPageBackground.png";
import BasicLayout from "main/layouts/BasicLayout/BasicLayout";
import { useParams } from "react-router-dom";
import { useBackend } from "main/utils/useBackend";


export default function ProfitsPage() {
    const { commonsId } = useParams();

    // Stryker disable all 
    const { data: profits } =
        useBackend(
            [`/api/profits/all/commonsid?commonsId=${commonsId}`],
            {
                method: "GET",
                url: "/api/profits/all/commonsid",
                params: {
                    commonsId: commonsId
                }
            }
        );
    // Stryker enable all 

    return (
        <div style={{ backgroundSize: 'cover', backgroundImage: `url(${Background})` }} data-testid="profitspage-div">
            <BasicLayout>
                { !!profits &&
                <Profits profits={Array.from(profits)} showAll />}
            </BasicLayout>
        </div>
    )
}