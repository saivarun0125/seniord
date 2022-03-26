import * as React from "react";
import {  Button, ButtonVariant, Datagrid, DropdownDropDirection } from 'react-magma-dom';
import { ExportToCsv } from 'export-to-csv';

export default function RawData(props:{columns: any[], rows: any[], export: boolean}) {

    const options = { 
        fieldSeparator: ',',
        quoteStrings: '"',
        decimalSeparator: '.',
        showLabels: true, 
        showTitle: false,
        useTextFile: false,
        useBom: true,
        useKeysAsHeaders: true,
    };

    function export_data() {
        const csvExporter = new ExportToCsv(options);
        csvExporter.generateCsv(props.rows);
    }

    return (<>
            <Datagrid
                columns={props.columns}
                rows={props.rows}
                componentsProps={{
                    pagination: { dropdownDropDirection: DropdownDropDirection.down },
                }}
            />
            {props.export && (<Button onClick={export_data} variant={ButtonVariant.outline}> Export </Button>)}
            </>
    );
}