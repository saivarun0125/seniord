import * as React from "react";
const vis = require("./../../js/vis-timeline-graph2d.min.js");

// Configuration for the Timeline
const options = {};
var groups = [{
  id: 1,
  content: "Release Windows"
},{
  id: 2,
  content: "Assignments"
}];


export default function ReleaseWindows(props:{items: any[]}) {

  React.useEffect(() => {
    var container: HTMLElement = document.getElementById('timeline');
    var dataset = new vis.DataSet(props.items);

    var timeline = new vis.Timeline(container, dataset, groups, options);
  }, []);

  return (<><div id="timeline"></div>
      </>);
}
