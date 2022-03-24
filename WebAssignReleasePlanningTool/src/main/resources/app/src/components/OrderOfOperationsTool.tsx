import * as React from "react";
import { 
    Button, 
    Breadcrumb, 
    BreadcrumbItem,
    Select, 
    Label,
    Flex, 
    FlexBehavior
} from 'react-magma-dom';

export default function OrderOfOperationsTool() {
    const dummyOptions = [
        {
          label: "release #1",
          value: "25"
        },
        {
          label: "release #2",
          value: "60"
        },
        {
          label: "release #3",
          value: "45"
        }
      ];

    return(<>
        <Breadcrumb style={{padding:"20px"}}>
            <BreadcrumbItem to="/home">Home</BreadcrumbItem>
            <BreadcrumbItem>Order of Operations Tool</BreadcrumbItem>
        </Breadcrumb>
        <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
            <Flex behavior={FlexBehavior.item} xs={1}>
                <Label>Create Release: </Label>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={1}>
                    <Button> + </Button>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={10}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={1}>
                <Label>OR</Label>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={11}>
            </Flex>
            <Flex behavior={FlexBehavior.item} xs={2}>
                <Select id="existingRelease" labelText="Select Existing Release:" placeholder="Select..." items={dummyOptions} />
            </Flex>
        </Flex>
    </>);
}