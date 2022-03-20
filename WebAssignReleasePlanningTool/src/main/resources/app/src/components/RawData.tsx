import * as React from "react";
import {  Button, Datagrid, DropdownDropDirection } from 'react-magma-dom';

export default function RawData(props:{columns: any[], rows: any[]}) {

    return (<>
            <Datagrid
                columns={props.columns}
                rows={props.rows}
                componentsProps={{
                    pagination: { dropdownDropDirection: DropdownDropDirection.down },
                }}
            />
            <Button> Export </Button>
            </>
    );
}