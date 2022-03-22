import * as React from "react";
import {  Button, ButtonVariant, Datagrid, DropdownDropDirection } from 'react-magma-dom';
import { ExportToCsv } from 'export-to-csv';

export default function RawData(props:{columns: any[], rows: any[]}) {

    const options = { 
        fieldSeparator: ',',
        quoteStrings: '"',
        decimalSeparator: '.',
        showLabels: true, 
        showTitle: true,
        title: 'raw_data',
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
            <Button onClick={export_data} variant={ButtonVariant.outline}> Export </Button>
            </>
    );
}