import React from "react";
import Profits from "main/components/Commons/Profits";
import Background from "../../assets/PlayPageBackground.png";
import BasicLayout from "main/layouts/BasicLayout/BasicLayout";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";


export default function ProfitsPage({ profits, commonsId }) {
    const navigate = useNavigate();
    const visitButtonClick = (id) => { navigate("/play/" + id) };

    return (
        <div style={{ backgroundSize: 'cover', backgroundImage: `url(${Background})`}} data-testid="profitspage-div">
            <BasicLayout>
                <Button onClick={visitButtonClick(commonsId)}>Go Back</Button>
                <p/>
                <Profits profits={Array.from(profits)} showAll/>
            </BasicLayout>
        </div>
    )
}