import * as React from "react";
const vis = require("./../../js/vis-timeline-graph2d.min.js");


// Create a DataSet (allows two way data-binding)
var items = new vis.DataSet([
  { id: 1, style:"background-color: pink;",content: "Exam #1", start: "2014-04-20" },
  { id: 2, content: "Quiz 2", start: "2014-04-14 8:00 am", end: "2014-04-17 5:00 pm" },
  { id: 3, content: "Homework 3", start: "2014-04-18" },
  { id: 4, style:"background-color: pink;",content: "Exam 4", start: "2014-04-16", end: "2014-04-17" },
  { id: 5, content: "Homework 4", start: "2014-04-15", end: "2014-04-17" },
  { id: 6, content: "Physics Quiz 1", start: "2014-04-14", end: "2014-04-16" },
]);

// Configuration for the Timeline
const options = {};


export default function ReleaseWindows(props:{labels: any[], releaseWindows: any[], usage: any[]}) {

  React.useEffect(() => {
    var container: HTMLElement = document.getElementById('timeline');
    console.log(container);

    var timeline = new vis.Timeline(container,items, options);
  }, []);

  return (<><div id="timeline"></div>
      </>);
}
