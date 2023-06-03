import React from "react";
import ProfitsPage from "main/pages/ProfitsPage";
import profitsFixtures from "fixtures/profitsFixtures";

export default {
    title: "pages/ProfitsPage",
    component: ProfitsPage,
};

const Template = () => <ProfitsPage profits={profitsFixtures.threeProfits} commonsId={2}/>;

export const Default = Template.bind({});