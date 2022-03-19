import * as React from "react";
import { useState, useRef } from "react";
const vis = require("./../../js/vis-timeline-graph2d.min.js");

interface Assignment {
  id: Number,
  group: Number,
  content: String,
  start: String,
  end: String,
  type: String,
  className?: String,
  score?: String
}

function itemOver (item: Assignment, event: any) {
  console.log(item);
}

// Configuration for the Timeline
const options = {
  showTooltips: true,
  cluster: {
    maxItems: 5,
    clusterCriteria: function clusterCriteria(firstItem: Assignment, secondItem: Assignment) : boolean {
      if(!("className" in firstItem) && !("className" in firstItem))
        return true;

      if(!("className" in firstItem) || !("className" in firstItem))
        return false;

      if(firstItem.className === "item-release-window" || secondItem.className === "item-release-window") {
        return false;
      }

      if(firstItem.className === "item-exam" || secondItem.className === "item-exam") {
        return false;
      }

      return true;
    }
  },
  orientation: {
    axis: "both"
  }
};
var groups = [{
  id: 1,
  content: "Release Windows"
},{
  id: 2,
  content: "Assignments"
}];


export default function ReleaseWindows(props:{items: any[]}) {
  const [timeline, setTimeline] = useState(null as any);
  var items = useRef(new vis.DataSet([]));

  React.useEffect(() => {
    var container: HTMLElement = document.getElementById('timeline');
    container.innerHTML = '';
    items.current.clear();
    items.current.add(props.items);

    setTimeline(new vis.Timeline(container, items.current, groups, options));
  }, [props.items]);

  return (<><div id="timeline"></div></>);
}
