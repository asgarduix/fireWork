function createTable(tableId, columns, tableConfigs = {}) {
    // tableId: HTML element id
    // columns: tabulator formatted columns
    // tableConfigs: any tabulator params

    let tableConfigsDefault = {
        height: "100%", //根據資料行高設定, 100%為資料全展開 無scroll bar, 建議根據資料筆數再用 .setHeight("450px")重設
        layout: "fitColumns", //尚有其他格式 參考 http://tabulator.info/docs/4.9/layout
        selectable: true, //selectable與selectableRangeMode為 shift多選的用法
        selectableRangeMode: "click",
        history: false, //可還原上一步
        reactiveData: true,
        columnHeaderVertAlign: "center",
        columns: columns,
        pagination: false, //設定為"local"即可開啟
        paginationSize: 200,
        paginationButtonCount: 5,
        paginationAddRow: "page", //page新增於該頁, table新增於第一頁
        dataSorted: function(sorters, rows) {
            $(`#${tableId}-pagination .curPageNum`).val(1)
        },
        rowAdded: function(row) {},
        rowSelected: function(row) {},
        rowDeselected: function(row) {},
        rowSelectionChanged: function(data, rows) {
            //可以抓到shift的選取事件
        },
        rowDeleted: function(row) {},
        rowClick: function(e, row) {},
        rowMouseOver: function(e, row) {},
        rowMouseLeave: function(e, row) {},
    }

    //更新外部傳入設定
    tableConfigsDefault = {...tableConfigsDefault, ...tableConfigs }

    //製作tabulator-pagination
    if (tableConfigsDefault.pagination) {
        //$(`#${tableId}`).before(`<div id="${tableId}-pagination"></div>`)
        var node = document.getElementById(tableId);
        node.insertAdjacentHTML('beforebegin', `<div id="${tableId}-pagination"></div>`);
        let paginationHTML = `<div class="tabulator-footer">
	<div class="tabulator-paginator">
		<div class="tabulator-page"><button title="First Page" class="first" name="first">
				⊼
			</button></div>
		<div class="tabulator-page"><button title="Prev Page" name="prev">&lt;</button>
		</div>
		<div class="tabulator-page"><button title="Next Page" name="next">&gt;</button>
		</div>
		<div class="tabulator-page"><button title="Last Page" class="last" name="last"> ⊼
			</button></div>
	</div>
	<div>總共 <span class="dataNum">0</span> 筆</div>
	<div>每頁 <span class="dpPerPageNum">0</span> 筆</div>
	<div>總共 <span class="pageNum">0</span> 頁</div>
	<div>
		<div>目前第</div>
		<div>
			<select class="curPageNum">
			</select>
		</div>
		<div>頁</div>
	</div>
</div>`
            //將標籤寫入
            //$(`#${tableId}-pagination`).html(paginationHTML)
        $(`#${tableId}-pagination`)[0].innerHTML = paginationHTML
            //綁定按鈕事件
        $(`#${tableId}-pagination .tabulator-page button`).each(function(index, element) {
            $(element).on("click", function(e) {
                btnChangePage(e.target.name)
            })
        })

        //select change事件
        $(`#${tableId}-pagination .curPageNum`).on("click", function(e) {
            curPageChange(e.target.value)
        })


        function curPageChange(val) {
            table.setPage(val)
        }

        function btnChangePage(type) {
            let tableCurPage = table.getPage()
            let tablePageMax = table.getPageMax()
            switch (type) {
                case "first":
                    table.setPage(1)
                    break
                case "next":
                    if (tableCurPage < tablePageMax) {
                        table.nextPage()
                    }
                    break
                case "prev":
                    if (tableCurPage > 1) {
                        table.previousPage()
                    }
                    break
                case "last":
                    table.setPage(tablePageMax)
                    break
            }
            $(`#${tableId}-pagination .curPageNum`).val(table.getPage())
        }

    }


    //製作tabulator本體
    let table = new Tabulator(`#${tableId}`, tableConfigsDefault);
    tableConfigsDefault.pagination && initTableFoot(tableId, table)


    return table
}


function initTableFoot(tableId, table) {
    table.setPage(1)
    let tableDataNum = table.getDataCount()
    let tablePageSize = table.getPageSize()
    let tableCurPage = table.getPage()
    let tablePageMax = table.getPageMax()
    $(`#${tableId}-pagination .dataNum`).text(tableDataNum)
    $(`#${tableId}-pagination .dpPerPageNum`).text(tablePageSize)
    $(`#${tableId}-pagination .pageNum`).text(tablePageMax)

    $(`#${tableId}-pagination .curPageNum`).val(tableCurPage)
    let pageOptsStr = [...new Array(tablePageMax).keys()].map((index) => {
            return `<option value="${index + 1}">${index + 1}</option>`
        }).join("")
        //$(`#${tableId}-pagination .curPageNum`).empty().html(pageOptsStr)
    $(`#${tableId}-pagination .curPageNum`).empty()[0].innerHTML = pageOptsStr
}