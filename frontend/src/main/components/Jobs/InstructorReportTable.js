import React from "react";
import OurTable, {PlaintextColumn, DateColumn} from "main/components/OurTable";

export default function InstructorReportTable({ reports }) {

    const testid = "InstructorReportTable";

    const columns = [
        {
            Header: 'id',
            accessor: 'id', // accessor is the "key" in the data
        },
        DateColumn('Created', (cell)=>cell.row.original.createdAt),
        DateColumn('Updated', (cell)=>cell.row.original.updatedAt),
        {
            Header:'Name',
            accessor: 'name'
        },
        {
            Header:'Email',
            accessor: 'email'
        },
        {
            Header:'Number of Cows',
            accessor: 'numCows'
        },
        {
            Header:'Cow Health',
            accessor: 'cowHP'
        },
        {
            Header:'Wealth',
            accessor: 'wealth'
        },
        {
            Header:'Total Cows Bought',
            accessor: 'cowsBought'
        },
        {
            Header:'Total Cows Sold',
            accessor: 'cowsSold'
        },
        {
            Header:'Total Cows Died',
            accessor: 'cowsDead'
        },
        PlaintextColumn('Log', (cell)=>cell.row.original.log),
    ];
    
    const sortees = React.useMemo(
        () => [
          {
            id: "id",
            desc: true
          }
        ],
       // Stryker disable next-line all
        []
      );

    return <OurTable
        data={reports}
        columns={columns}
        testid={testid}
        initialState={{ sortBy: sortees }}
    />;
}; 
