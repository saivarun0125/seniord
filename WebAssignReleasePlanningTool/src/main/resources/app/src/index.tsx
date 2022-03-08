import * as React from "react";
import { useState } from "react";
import * as ReactDOM from "react-dom";
import { 
    Breadcrumb, 
    BreadcrumbItem,
    Button,
    ButtonVariant,
    Flex, 
    FlexBehavior,
    TabsOrientation
} from 'react-magma-dom';
import DueDateReport from './components/DueDateReport'

function HomePage() {
  const [redirect, setRedirect] = useState("Home");

  var output = <></>;
  if(redirect === "Home") {
    output = <>
      <Breadcrumb style={{padding:"20px"}}>
          <BreadcrumbItem>Home</BreadcrumbItem>
      </Breadcrumb>
      <Flex behavior={FlexBehavior.container} spacing={3} style={{padding:"40px"}}>
        <Flex behavior={FlexBehavior.item} xs={12}>
          <Button onClick={() => {setRedirect("DueDateReport")}} variant={ButtonVariant.outline}>
          Due Date Report
          </Button>
        </Flex>
        <Flex behavior={FlexBehavior.item} xs={12}>
          <Button onClick={() => {setRedirect("UsageReport")}} variant={ButtonVariant.outline}>
          Usage Report
          </Button>
        </Flex>
        <Flex behavior={FlexBehavior.item} xs={12}>
          <Button onClick={() => {setRedirect("OrderOfOperations")}} variant={ButtonVariant.outline}>
          Order of operations tool
          </Button>
        </Flex>
      </Flex>
      </>;
  }
  else if(redirect === "DueDateReport") {
    output = <DueDateReport />;
  }
  else if(redirect === "UsageReport") {
    output = <div>UsageReport</div>;
  }
  else {
    output = <div>OrderOfOperations</div>;
  }

  return (output);
}
ReactDOM.render(
  <HomePage />,
    document.getElementById("root")
);